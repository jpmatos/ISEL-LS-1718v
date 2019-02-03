package pt.isel.ls.commands.methods.get.unused;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.SessionsDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@PathInfo(pathMethod = "/sessions/date/today")
public class SessionsDateToday extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateSearch = LocalDateTime.now();
        String dateStartOfDay = dtf.format(dateSearch).concat(" 00:00:00");
        String dateEndOfDay = dtf.format(dateSearch).concat(" 23:59:59");
        String sql = "SELECT * FROM Sessions " +
                "WHERE Sessions._date BETWEEN '" + dateStartOfDay + "' AND '" + dateEndOfDay + "'";
        SessionsDataMapper sessionsDataMapper = new SessionsDataMapper();
        c.iterableResult = sessionsDataMapper.Get(sql, c.dataSource);
        return c;
    }
}
