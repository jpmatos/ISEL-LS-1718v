package pt.isel.ls.mappers;
import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.tables.Cinemas;
import pt.isel.ls.utils.annotations.MapperInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@MapperInfo(dto = "Cinemas", table = "cinemas")
public class CinemasDataMapper extends AbstractDataMapper {

    final String COLUMNS = "_id, _name, _city";
    final String SQL_GET_ALL = "SELECT " + COLUMNS + " FROM Cinemas";
    final String SQL_INSERT = "INSERT INTO Cinemas (" + COLUMNS + ") VALUES ";
    final String SQL_DELETE = "DELETE FROM Cinemas WHERE _id = ";

    @Override
    protected String SqlGetAll() {
        return SQL_GET_ALL;
    }

    @Override
    protected PreparedStatement SqlInsert(Object target, PGSimpleDataSource dataSource) throws SQLException {
        Cinemas c = (Cinemas)target;
        String values = "(?, ?, ?)";
        String sql = SQL_INSERT + values;
        Connection con = dataSource.getConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, c.getiD());
        statement.setString(2, c.getName());
        statement.setString(3, c.getCity());
        return statement;
    }

    @Override
    protected String SqlDelete(Object target) {
        Cinemas c = (Cinemas)target;
        return SQL_DELETE + c.getiD();
    }

    @Override
    protected Object Load(ResultSet rs) {
        Cinemas c = new Cinemas();
        try {
            c.setiD(rs.getInt("_id"));
            c.setName(rs.getString("_name"));
            c.setCity(rs.getString("_city"));
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected Object ObjectMap(LinkedHashMap<String, ArrayList<String>> path, int iD) {
        return new Cinemas(iD,
                path.get("name").iterator().next(),
                path.get("city").iterator().next()
        );
    }

    @Override
    protected Object[] ObjectMapArray(LinkedHashMap<String, ArrayList<String>> path, int iD) {
        //TODO Implement
        return new Object[0];
    }

    @Override
    protected String SqlDeleteWithoutId(Object target) {
        //TODO Implement
        return null;
    }

}
