package pt.isel.ls.commands.methods.get.unused;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TicketsDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

@PathInfo(pathMethod = "/theaters/var/sessions/var/tickets/var")
public class TheatersIdSessionsIdTicketsId extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Tickets " +
                "INNER JOIN Sessions ON (Sessions._id = Tickets._sid) " +
                "INNER JOIN Theaters ON (Theaters._id = Sessions._tid) " +
                "WHERE Sessions._tid = " + c.pathValues.get("theaters") +
                " AND Tickets._sid = " + c.pathValues.get("sessions") +
                " AND Tickets._id = " + c.pathValues.get("tickets");
        TicketsDataMapper ticketsDataMapper = new TicketsDataMapper();
        c.iterableResult = ticketsDataMapper.Get(sql, c.dataSource);
        return c;
    }
}
