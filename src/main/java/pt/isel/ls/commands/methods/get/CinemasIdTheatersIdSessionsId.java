package pt.isel.ls.commands.methods.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.SessionsDataMapper;
import pt.isel.ls.mappers.TicketsDataMapper;
import pt.isel.ls.tables.Sessions;
import pt.isel.ls.tables.Tickets;
import pt.isel.ls.utils.annotations.PathInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@PathInfo(pathMethod = "/cinemas/var/theaters/var/sessions/var")
public class CinemasIdTheatersIdSessionsId extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Sessions " +
                "INNER JOIN Theaters ON (Theaters._id = Sessions._tid) " +
                "INNER JOIN Cinemas ON (Cinemas._id = Theaters._cid) " +
                "WHERE Sessions._cid = " + c.pathValues.get("cinemas") +
                " AND Sessions._tid = " + c.pathValues.get("theaters") +
                " AND Sessions._id = " + c.pathValues.get("sessions");
        SessionsDataMapper sessionsDataMapper = new SessionsDataMapper();
        c.iterableResult = sessionsDataMapper.Get(sql, c.dataSource);
        c.neighbourList = addNeighbourList(c);
        c.neighbourCommands = addNeighbourCommands(c);
        c.replaceView("text/html/get/cinemasIdtheatersIdsessionsId");
        return c;
    }

    private ArrayList<String> addNeighbourCommands(Command c) {
        ArrayList<String> res = new ArrayList<>();
        Sessions session = (Sessions) c.iterableResult.iterator().next();
        res.add("cinemas/" + session.getCid() + "/theaters/" + session.getTid());
        res.add("cinemas/" + session.getCid() + "/sessions/date/today");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = session.getDate().split(" ")[0];
        LocalDate date = LocalDate.parse(dateString, formatter);
        res.add("cinemas/" + session.getCid() + "/sessions/date/" + date);
        res.add("movies/" + session.getMid());
        return res;
    }

    private ArrayList<String> addNeighbourList(Command c) {
        ArrayList<String> res = new ArrayList<>();
        Sessions session = (Sessions) c.iterableResult.iterator().next();
        String sql = "SELECT * FROM Tickets " +
                "INNER JOIN Sessions ON (Sessions._id = Tickets._sid) " +
                "INNER JOIN Theaters ON (Theaters._id = Sessions._tid) " +
                "INNER JOIN Cinemas ON (Cinemas._id = Theaters._cid) " +
                "WHERE Sessions._cid = " + session.getCid() +
                " AND Sessions._tid = " + session.getTid() +
                " AND Sessions._id = " + session.getiD();
        TicketsDataMapper ticketsDataMapper = new TicketsDataMapper();
        Iterable tickets = ticketsDataMapper.Get(sql, c.dataSource);
        for (Object object : tickets) {
            Tickets ticket = (Tickets) object;
            res.add("cinemas/" + session.getCid() +
                    "/theaters/" + session.getTid() +
                    "/sessions/" + ticket.getSid() +
                    "/tickets/" + ticket.getId() );
        }
        return res;
    }
}
