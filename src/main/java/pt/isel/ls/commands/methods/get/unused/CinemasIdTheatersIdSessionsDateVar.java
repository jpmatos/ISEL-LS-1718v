package pt.isel.ls.commands.methods.get.unused;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.SessionsDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.Iterator;

@PathInfo(pathMethod = "/cinemas/var/theaters/var/sessions/date/var")
public class CinemasIdTheatersIdSessionsDateVar extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        Iterator<String> pathMethodValue = c.pathMethodValues.iterator();
        String date = pathMethodValue.next();
        String dateStartOfDay = date.replace("+", "-").concat(" 00:00:00");
        String dateEndOfDay = date.replace("+", "-").concat(" 23:59:59");
        String sql = "SELECT * FROM Sessions " +
                "INNER JOIN Theaters ON (Theaters._id = Sessions._tid) " +
                "INNER JOIN Cinemas ON (Cinemas._id = Theaters._cid) " +
                "WHERE Sessions._cid = " + c.pathValues.get("cinemas") +
                " AND Sessions._tid = " + c.pathValues.get("theaters") +
                " AND Sessions._date BETWEEN '" + dateStartOfDay + "' AND '" + dateEndOfDay + "'";
        SessionsDataMapper sessionsDataMapper = new SessionsDataMapper();
        c.iterableResult = sessionsDataMapper.Get(sql, c.dataSource);
        return c;
    }
}
