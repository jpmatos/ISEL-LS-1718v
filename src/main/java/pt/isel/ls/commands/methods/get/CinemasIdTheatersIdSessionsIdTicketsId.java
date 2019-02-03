package pt.isel.ls.commands.methods.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TicketsDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/cinemas/var/theaters/var/sessions/var/tickets/var")
public class CinemasIdTheatersIdSessionsIdTicketsId extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Tickets " +
                "INNER JOIN Sessions ON (Sessions._id = Tickets._sid) " +
                "INNER JOIN Theaters ON (Theaters._id = Sessions._tid) " +
                "INNER JOIN Cinemas ON (Cinemas._id = Theaters._cid) " +
                "WHERE Sessions._cid = " + c.pathValues.get("cinemas") +
                " AND Sessions._tid = " + c.pathValues.get("theaters") +
                " AND Tickets._sid = " + c.pathValues.get("sessions") +
                " AND Tickets._id = " + c.pathValues.get("tickets");
        TicketsDataMapper ticketsDataMapper = new TicketsDataMapper();
        c.iterableResult = ticketsDataMapper.Get(sql, c.dataSource);
        c.neighbourCommands = addNeighbourCommands(c);
        c.replaceView("text/html/get/cinemasIdtheatersIdsessionsIdticketsId");
        return c;
    }

    private ArrayList<String> addNeighbourCommands(Command c) {
        ArrayList<String> res = new ArrayList<>();
        res.add("cinemas/" + c.pathValues.get("cinemas") +
                "/theaters/" + c.pathValues.get("theaters") +
                "/sessions/" + c.pathValues.get("sessions"));
        return res;
    }
}
