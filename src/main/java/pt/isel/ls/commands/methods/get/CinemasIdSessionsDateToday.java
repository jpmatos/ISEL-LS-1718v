package pt.isel.ls.commands.methods.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.SessionsDataMapper;
import pt.isel.ls.tables.Sessions;
import pt.isel.ls.utils.annotations.PathInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@PathInfo(pathMethod = "/cinemas/var/sessions/date/today")
public class CinemasIdSessionsDateToday extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateSearch = LocalDateTime.now();
        String dateStartOfDay = dtf.format(dateSearch).concat(" 00:00:00");
        String dateEndOfDay = dtf.format(dateSearch).concat(" 23:59:59");
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
        LocalDate todayDate = LocalDate.now();
        String nextDay = formatter.format(todayDate.plusDays(1));
        String previousDay = formatter.format(todayDate.minusDays(1));
        res.add("cinemas/" + c.pathValues.get("cinemas") + "/sessions/date/" + nextDay);
        res.add("cinemas/" + c.pathValues.get("cinemas") + "/sessions/date/" + previousDay);
        return res;
    }
}
