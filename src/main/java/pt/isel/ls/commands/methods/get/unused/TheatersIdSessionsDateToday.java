package pt.isel.ls.commands.methods.get.unused;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TicketsDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@PathInfo(pathMethod = "/theaters/var/sessions/date/today")
public class TheatersIdSessionsDateToday extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateSearch = LocalDateTime.now();
        String dateStartOfDay = dtf.format(dateSearch).concat(" 00:00:00");
        String dateEndOfDay = dtf.format(dateSearch).concat(" 23:59:59");
        String sql = "SELECT * FROM Tickets " +
                "INNER JOIN Sessions ON (Sessions._id = Tickets._sid) " +
                "INNER JOIN Theaters ON (Theaters._id = Sessions._tid) " +
                "WHERE Sessions._tid = " + c.pathValues.get("theaters") +
                " AND Tickets._sid = " + c.pathValues.get("sessions") +
                " AND Tickets._id = " + c.pathValues.get("tickets") +
                " AND Sessions._date BETWEEN '" + dateStartOfDay + "' AND '" + dateEndOfDay + "'";
        TicketsDataMapper ticketsDataMapper = new TicketsDataMapper();
        c.iterableResult = ticketsDataMapper.Get(sql, c.dataSource);
        return c;
    }
}
