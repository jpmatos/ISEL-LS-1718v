package pt.isel.ls.commands.methods.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.SessionsDataMapper;
import pt.isel.ls.tables.Sessions;
import pt.isel.ls.utils.annotations.PathInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

@PathInfo(pathMethod = "/cinemas/var/sessions/date/var")
public class CinemasIdSessionsDateVar extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        Iterator<String> pathMethodValue = c.pathMethodValues.iterator();
        String date = pathMethodValue.next();
        String dateStartOfDay = date.replace("+", "-").concat(" 00:00:00");
        String dateEndOfDay = date.replace("+", "-").concat(" 23:59:59");
        String sql = "SELECT * FROM Sessions " +
                "INNER JOIN Cinemas ON (Cinemas._id = Sessions._cid) " +
                "WHERE Sessions._cid = " + c.pathValues.get("cinemas") +
                " AND Sessions._date BETWEEN '" + dateStartOfDay + "' AND '" + dateEndOfDay + "'";
        SessionsDataMapper sessionsDataMapper = new SessionsDataMapper();
        c.iterableResult = sessionsDataMapper.Get(sql, c.dataSource);
        c.neighbourList = addNeighbourCommands(c);

        c.replaceView("text/html/get/cinemasIdtheatersIdsessionsDate");
        return c;
    }

    private ArrayList<String> addNeighbourCommands(Command c) {
        ArrayList<String> res = new ArrayList<>();
        res.add("cinemas/" + c.pathValues.get("cinemas"));
        for (Object object : c.iterableResult) {
            Sessions session = (Sessions) object;
            res.add("cinemas/" + session.getCid() + "/theaters/" + session.getTid() + "/sessions/" + session.getiD());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = c.pathMethodValues.iterator().next();
        LocalDate date = LocalDate.parse(dateString, formatter);
        String nextDay = formatter.format(date.plusDays(1));
        String previousDay = formatter.format(date.minusDays(1));
        res.add("cinemas/" + c.pathValues.get("cinemas") + "/sessions/date/" + nextDay);
        res.add("cinemas/" + c.pathValues.get("cinemas") + "/sessions/date/" + previousDay);
        return res;
    }
}
