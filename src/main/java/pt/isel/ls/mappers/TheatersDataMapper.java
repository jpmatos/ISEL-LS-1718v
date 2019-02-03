package pt.isel.ls.mappers;
import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.tables.Theaters;
import pt.isel.ls.utils.annotations.MapperInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@MapperInfo(dto = "Theaters", table = "theaters")
public class TheatersDataMapper extends AbstractDataMapper {

    final String COLUMNS = "_id, _name, _rows, _availableSeats, _seats, _cid";
    final String SQL_GET_ALL = "SELECT " + COLUMNS + " FROM Theaters";
    final String SQL_INSERT = "INSERT INTO Theaters (" + COLUMNS + ") VALUES ";
    final String SQL_DELETE = "DELETE FROM Theaters WHERE _id = ";

    @Override
    protected String SqlGetAll() {
        return SQL_GET_ALL;
    }

    @Override
    protected PreparedStatement SqlInsert(Object target, PGSimpleDataSource dataSource) throws SQLException {
        Theaters t = (Theaters) target;
        String values = "(?, ?, ?, ?, ?, ?)";
        String sql = SQL_INSERT + values;
        Connection con = dataSource.getConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, t.getiD());
        statement.setString(2, t.getName());
        statement.setInt(3, t.getRows());
        statement.setInt(4, t.getAvailableSeats());
        statement.setInt(5, t.getSeats());
        statement.setInt(6, t.getCid());
        return statement;
    }

    @Override
    protected String SqlDelete(Object target) {
        Theaters t = (Theaters) target;
        return SQL_DELETE + t.getiD();
    }

    @Override
    protected Object Load(ResultSet rs) {
        Theaters t = new Theaters();
        try {
            t.setiD(rs.getInt("_id"));
            t.setName(rs.getString("_name"));
            t.setRows(rs.getInt("_rows"));
            t.setAvailableSeats(rs.getInt("_rows") * rs.getInt("_seats"));
            t.setSeats(rs.getInt("_seats"));
            t.setCid(rs.getInt("_cid"));
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected Object ObjectMap(LinkedHashMap<String, ArrayList<String>> path, int iD) {
        return new Theaters(iD,
                path.get("name").iterator().next(),
                Integer.parseInt(path.get("rows").iterator().next()),
                Integer.parseInt(path.containsKey("availableSeats") ? path.get("availableSeats").iterator().next() : path.get("seats").iterator().next()),
                Integer.parseInt(path.get("seats").iterator().next()),
                Integer.parseInt(path.get("cinemas").iterator().next())
                );
    }

    @Override
    protected Object[] ObjectMapArray(LinkedHashMap<String, ArrayList<String>> path, int iD) {
        throw new NotImplementedException();
    }

    @Override
    protected String SqlDeleteWithoutId(Object target) {
        throw new NotImplementedException();
    }
}
