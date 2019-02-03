package pt.isel.ls.mappers;
import com.google.common.collect.Iterables;
import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.tables.Movies;
import pt.isel.ls.tables.Sessions;
import pt.isel.ls.utils.annotations.MapperInfo;
import pt.isel.ls.utils.exceptions.SessionTimeOverlapException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@MapperInfo(dto = "Sessions", table = "sessions")
public class SessionsDataMapper extends AbstractDataMapper {

    final String COLUMNS = "_id, _date, _mid, _tid, _cid";
    final String SQL_GET_ALL = "SELECT " + COLUMNS + " FROM Sessions";
    final String SQL_INSERT = "INSERT INTO Sessions (" + COLUMNS + ") VALUES ";
    final String SQL_DELETE = "DELETE FROM Sessions WHERE _id = ";

    @Override
    protected String SqlGetAll() {
        return SQL_GET_ALL;
    }

    @Override
    protected PreparedStatement SqlInsert(Object target, PGSimpleDataSource dataSource) throws SQLException, SessionTimeOverlapException, ParseException {
        Sessions s = (Sessions)target;
        if (CheckAvailableTime(s, dataSource)) {
            String values = "(?, ?, ?, ?, ?)";
            String sql = SQL_INSERT + values;
            java.sql.Timestamp sqlDate = convertStringToSqlDate(s.getDate());
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, s.getiD());
            statement.setTimestamp(2,sqlDate);
            statement.setInt(3, s.getMid());
            statement.setInt(4, s.getTid());
            statement.setInt(5, s.getCid());
            return statement;
        } else throw new SessionTimeOverlapException();
    }

    private boolean CheckAvailableTime(Sessions s, PGSimpleDataSource dataSource) {
        //get movie duration
        String sql = "SELECT * FROM Movies WHERE _id = " + s.getMid();
        MoviesDataMapper moviesDataMapper = new MoviesDataMapper();
        Iterable<Movies> movies = moviesDataMapper.Get(sql, dataSource);
        int minutes = movies.iterator().next().getDuration();

        //try to get a session before or after
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String getDate = s.getDate();
        LocalDateTime dateStart = LocalDateTime.parse(getDate, formatter);
        LocalDateTime dateEnd = dateStart.plusMinutes(minutes);

        sql = "SELECT * FROM Sessions WHERE _tid = " + s.getTid() + " AND _date >= '" + dateStart.toString().replace("T", " ") + "' AND _date <= '" + dateEnd.toString().replace("T", " ") + "'";
        Iterable<Sessions> sessions = this.Get(sql, dataSource);
        return Iterables.size(sessions) <= 0;
    }

    @Override
    protected String SqlDelete(Object target) {
        Sessions s = (Sessions) target;
        return SQL_DELETE + s.getiD();
    }

    @Override
    protected Object Load(ResultSet rs) {
        Sessions s = new Sessions();
        try {
            s.setiD(rs.getInt("_id"));
            s.setDate(rs.getString("_date"));
            s.setMid(rs.getInt("_mid"));
            s.setTid(rs.getInt("_tid"));
            s.setCid(rs.getInt("_cid"));
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected Object ObjectMap(LinkedHashMap<String, ArrayList<String>> path, int iD) {
        return new Sessions(iD,
                path.get("date").iterator().next(),
                Integer.parseInt(path.get("mid").iterator().next()),
                Integer.parseInt(path.get("theaters").iterator().next()),
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
