package pt.isel.ls.commands.methods.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.CinemasDataMapper;
import pt.isel.ls.mappers.SessionsDataMapper;
import pt.isel.ls.mappers.TheatersDataMapper;
import pt.isel.ls.tables.Sessions;
import pt.isel.ls.tables.Theaters;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/cinemas/var")
public class CinemasId extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Cinemas " +
                "WHERE Cinemas._id = " + c.pathValues.get("cinemas");
        CinemasDataMapper cinemasDataMapper = new CinemasDataMapper();
        c.iterableResult = cinemasDataMapper.Get(sql, c.dataSource);
        c.neighbourList = addNeighbourList(c);
        c.neighbourCommands = addNeighbourCommands(c);
        c.replaceView("text/html/get/cinemasId");
        return c;
    }

    private ArrayList<String> addNeighbourCommands(Command c) {
        ArrayList<String> res = new ArrayList<>();
        String sql = "SELECT * FROM Sessions " +
                "INNER JOIN Cinemas ON (Cinemas._id = Sessions._cid) " +
                "WHERE Sessions._cid = " + c.pathValues.get("cinemas");
        SessionsDataMapper sessionsDataMapper = new SessionsDataMapper();
        Iterable sessions = sessionsDataMapper.Get(sql, c.dataSource);
        for (Object object : sessions) {
            Sessions session = (Sessions) object;
            res.add("movies/" + session.getMid());
        }
        return res;
    }

    private ArrayList<String> addNeighbourList(Command c) {
        ArrayList<String> res = new ArrayList<>();

        String sql = "SELECT * FROM Theaters " +
                "INNER JOIN Cinemas ON (Cinemas._id = Theaters._cid) " +
                "WHERE Cinemas._id = " + c.pathValues.get("cinemas");
        TheatersDataMapper theatersDataMapper = new TheatersDataMapper();
        Iterable theaters = theatersDataMapper.Get(sql, c.dataSource);
        for (Object object : theaters) {
            Theaters theater = (Theaters)object;
            res.add("cinemas/" + c.pathValues.get("cinemas") + "/theaters/" + theater.getiD());
        }
        return res;
    }
}
