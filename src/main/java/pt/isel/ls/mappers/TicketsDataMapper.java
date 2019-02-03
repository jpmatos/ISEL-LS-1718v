package pt.isel.ls.mappers;

import com.google.common.collect.Iterables;
import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.tables.Theaters;
import pt.isel.ls.tables.Tickets;
import pt.isel.ls.utils.annotations.MapperInfo;
import pt.isel.ls.utils.exceptions.NoAvailableSeatsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

@MapperInfo(dto = "Tickets", table = "tickets")
public class TicketsDataMapper extends AbstractDataMapper {
    private final String COLUMNS = "_id, _row, _seat, _sid";
    private final String SQL_GET_ALL = "SELECT * FROM Tickets";
    private final String SQL_INSERT = "INSERT INTO Tickets(" + COLUMNS + ") VALUES";
    private final String SQL_DELETE = "DELETE FROM Tickets WHERE ";

    @Override
    protected String SqlGetAll() {
        return SQL_GET_ALL;
    }

    @Override
    protected PreparedStatement SqlInsert(Object target, PGSimpleDataSource dataSource) throws SQLException, NoAvailableSeatsException {
        Tickets t = (Tickets) target;
        if(checkAvailableSeats(t, dataSource)) {
            String values = "(?, ?, ?, ?)";
            String sql = SQL_INSERT + values;
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, t.getId());
            statement.setString(2, t.getRow());
            statement.setInt(3, t.getSeat());
            statement.setInt(4, t.getSid());
            return statement;
        } else throw new NoAvailableSeatsException();
    }

    private boolean checkAvailableSeats(Tickets t, PGSimpleDataSource dataSource) {
        String sql = "SELECT * FROM Tickets T WHERE _sid = " + t.getSid();
        Iterable<Tickets> tickets = this.Get(sql, dataSource);
        sql = "SELECT * FROM Theaters T INNER JOIN Sessions S ON (T._id=S._tid) WHERE S._id = " + t.getSid();
        TheatersDataMapper theatersDataMapper = new TheatersDataMapper();
        Iterable<Theaters> theaters = theatersDataMapper.Get(sql, dataSource);
        if(theaters.iterator().next().getAvailableSeats()>Iterables.size(tickets))
            return true;
        return false;
    }

    @Override
    protected String SqlDelete(Object target) {
        Tickets t = (Tickets)target;
        return SQL_DELETE + "_id = " + t.getId();
    }

    @Override
    protected String SqlDeleteWithoutId(Object target) {
        //SQL Ex: "DELETE FROM Tickets WHERE _cid = {cid} AND _tid = {tid} AND _sid = {sid} AND _id = 3 AND _row = 'A'"
        Tickets t = (Tickets)target;
        return SQL_DELETE + "_sid = " + t.getSid() + " AND _row = '" + t.getRow() + "' AND _seat = " + t.getSeat();
    }

    @Override
    protected Object Load(ResultSet rs) {
        Tickets t = new Tickets();
        try {
            t.setId(rs.getInt("_id"));
            t.setRow(rs.getString("_row"));
            t.setSeat(rs.getInt("_seat"));
            t.setSid(rs.getInt("_sid"));
            return t;
        }
        catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected Object ObjectMap(LinkedHashMap<String, ArrayList<String>> path, int iD) {
        return new Tickets(iD,
                path.get("row").iterator().next(),
                Integer.parseInt(path.get("seat").iterator().next()),
                Integer.parseInt(path.get("sessions").iterator().next())
                );
    }

    @Override
    protected Object[] ObjectMapArray(LinkedHashMap<String, ArrayList<String>> path, int iD) {
        int amount = path.get("tkid").size();
        Tickets[] res = new Tickets[amount];
        String value = "", key = "";
        Iterator it = path.get("tkid").iterator();
        for (int i = 0; i < amount; i++) {
            key = (String) it.next();

            value = getSeat(key);
            path = addValues("seat", value, path);

            value = getRow(key);
            path = addValues("row", value, path);

            res[i] = (Tickets) ObjectMap(path, iD);
            path.remove("seat");
            path.remove("row");
        }
        return res;
    }

    //get the char part
    private String getRow(String tkid) {
        return "" + tkid.charAt(0);
    }

    //get the int part
    private String getSeat(String tkid) {
        return tkid.substring(1);
    }

    //TODO This shouldn't be here (?)
    private static LinkedHashMap<String,ArrayList<String>> addValues(String s, String s1, LinkedHashMap<String, ArrayList<String>> res) {
        ArrayList<String> tempList = null;
        if (res.containsKey(s)) {
            tempList = res.get(s);
            if(tempList == null)
                tempList = new ArrayList<>();
            tempList.add(s1);
        } else {
            tempList = new ArrayList<>();
            tempList.add(s1);
        }
        res.put(s,tempList);
        return res;
    }
}
