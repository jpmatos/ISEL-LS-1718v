package pt.isel.ls.commands.methods.get.unused;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TicketsDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

@PathInfo(pathMethod = "/cinemas/var/theaters/var/sessions/var/tickets")
public class CinemasIdTheatersIdSessionsIdTickets extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Tickets " +
                "INNER JOIN Sessions ON (Sessions._id = Tickets._sid) " +
                "INNER JOIN Theaters ON (Theaters._id = Sessions._tid) " +
                "INNER JOIN Cinemas ON (Cinemas._id = Theaters._cid) " +
                "WHERE Sessions._cid = " + c.pathValues.get("cinemas") +
                " AND Sessions._tid = " + c.pathValues.get("theaters") +
                " AND Sessions._id = " + c.pathValues.get("sessions");
        TicketsDataMapper ticketsDataMapper = new TicketsDataMapper();
        c.iterableResult = ticketsDataMapper.Get(sql, c.dataSource);
        return c;
    }
}
