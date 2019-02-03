package pt.isel.ls.commands.methods.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.SessionsDataMapper;
import pt.isel.ls.mappers.TheatersDataMapper;
import pt.isel.ls.tables.Sessions;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/cinemas/var/theaters/var")
public class CinemasIdTheatersId extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Theaters " +
                "INNER JOIN Cinemas ON (Cinemas._id = Theaters._cid) " +
                "WHERE Theaters._cid = " + c.pathValues.get("cinemas") +
                " AND Theaters._id = " + c.pathValues.get("theaters");
        TheatersDataMapper theatersDataMapper = new TheatersDataMapper();
        c.iterableResult = theatersDataMapper.Get(sql, c.dataSource);
        c.neighbourList = addNeighbourList(c);
        c.neighbourCommands = addNeighbourCommands(c);
        c.replaceView("text/html/get/cinemasIdtheatersId");
        return c;
    }

    private ArrayList<String> addNeighbourCommands(Command c) {
        ArrayList<String> res = new ArrayList<>();
        res.add("cinemas/" + c.pathValues.get("cinemas"));
        return res;
    }

    private ArrayList<String> addNeighbourList(Command c) {
        ArrayList<String> res = new ArrayList<>();
        String sql = "SELECT * FROM Sessions " +
                "INNER JOIN Theaters ON (Theaters._id = Sessions._tid) " +
                "INNER JOIN Cinemas ON (Cinemas._id = Theaters._cid) " +
                "WHERE Sessions._cid = " + c.pathValues.get("cinemas") +
                " AND Sessions._tid = " + c.pathValues.get("theaters");
        SessionsDataMapper sessionsDataMapper = new SessionsDataMapper();
        Iterable sessions = sessionsDataMapper.Get(sql, c.dataSource);
        for (Object object : sessions) {
            Sessions session = (Sessions)object;
            res.add("cinemas/" + session.getCid() + "/theaters/" + session.getTid() + "/sessions/" + session.getiD());
        }
        return res;
    }
}
