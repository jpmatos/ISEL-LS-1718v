package pt.isel.ls.commands.methods.get.unused;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TicketsDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

@PathInfo(pathMethod = "/movies/var/sessions/var/tickets")
public class MoviesIdSessionsIdTickets extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Tickets " +
                "INNER JOIN Sessions ON (Sessions._id = Tickets._sid) " +
                "INNER JOIN Movies ON (Movies._id = Sessions._mid) " +
                "WHERE Sessions._mid = " + c.pathValues.get("movies") +
                " AND Tickets._sid = " + c.pathValues.get("sessions");
        TicketsDataMapper ticketsDataMapper = new TicketsDataMapper();
        c.iterableResult = ticketsDataMapper.Get(sql, c.dataSource);
        return c;
    }
}
