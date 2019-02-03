package pt.isel.ls.mappers;
import org.json.JSONObject;
import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.tables.Movies;
import pt.isel.ls.utils.annotations.MapperInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@MapperInfo(dto = "Movies", table = "movies")
public class MoviesDataMapper extends AbstractDataMapper {

    final String COLUMNS = "_id, _title, _releaseYear, _duration";
    final String SQL_GET_ALL = "SELECT " + COLUMNS + " FROM Movies";
    final String SQL_INSERT = "INSERT INTO Movies (" + COLUMNS + ") VALUES ";
    final String SQL_DELETE = "DELETE FROM Movies WHERE _id = ";

    @Override
    protected String SqlGetAll() {
        return SQL_GET_ALL;
    }

    @Override
    protected PreparedStatement SqlInsert(Object target, PGSimpleDataSource dataSource) throws SQLException {
        Movies m = (Movies)target;
        String values = "(?, ?, ?, ?)";
        String sql = SQL_INSERT + values;
        Connection con = dataSource.getConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, m.getiD());
        statement.setString(2, m.getTitle());
        statement.setInt(3, m.getReleaseYear());
        statement.setInt(4, m.getDuration());
        return statement;
    }

    @Override
    protected String SqlDelete(Object target) {
        Movies m = (Movies) target;
        return SQL_DELETE + m.getiD();
    }

    @Override
    protected Object Load(ResultSet rs) {
        Movies m = new Movies();
        try {
            m.setiD(rs.getInt("_id"));
            m.setTitle(rs.getString("_title"));
            m.setReleaseYear(rs.getInt("_releaseYear"));
            m.setDuration(rs.getInt("_duration"));
            return m;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected Object ObjectMap(LinkedHashMap<String, ArrayList<String>> path, int iD) {
        return new Movies(iD,
                path.get("title").iterator().next(),
                Integer.parseInt(path.get("releaseYear").iterator().next()),
                Integer.parseInt(path.get("duration").iterator().next())
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

    public Movies mapFromJSON(JSONObject json) {
        int id = json.getInt("id");
        String release_date = json.getString("release_date");
        int runtime = json.getInt("runtime");
        String original_title = json.getString("original_title");

        int release_year = Integer.parseInt(release_date.split("-")[0]);
        return new Movies(id, original_title, release_year, runtime);
    }
}
