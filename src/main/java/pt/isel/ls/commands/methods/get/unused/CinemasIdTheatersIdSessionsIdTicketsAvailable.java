package pt.isel.ls.commands.methods.get.unused;

import com.google.common.collect.Iterables;
import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TheatersDataMapper;
import pt.isel.ls.mappers.TicketsDataMapper;
import pt.isel.ls.tables.Theaters;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/cinemas/var/theaters/var/sessions/var/tickets/available")
public class CinemasIdTheatersIdSessionsIdTicketsAvailable extends AbstractPath {
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
        Iterable tickets = ticketsDataMapper.Get(sql, c.dataSource);

        sql = "SELECT * FROM Theaters " +
            "INNER JOIN Sessions ON (Sessions._id = Tickets._sid) " +
            "WHERE Sessions._id = " + c.pathValues.get("sessions");
        TheatersDataMapper theatersDataMapper = new TheatersDataMapper();
        Theaters theater = (Theaters) theatersDataMapper.Get(sql, c.dataSource).iterator().next();

        ArrayList<Integer> res = new ArrayList<>();
        res.add((theater.getAvailableSeats() - Iterables.size(tickets)));
        c.iterableResult = res;
        return c;
    }
}
