package pt.isel.ls.commands.methods.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.MoviesDataMapper;
import pt.isel.ls.mappers.SessionsDataMapper;
import pt.isel.ls.tables.Sessions;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/movies/var")
public class MoviesId extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Movies " +
                "WHERE Movies._id = " + c.pathValues.get("movies");
        MoviesDataMapper moviesDataMapper = new MoviesDataMapper();
        c.iterableResult = moviesDataMapper.Get(sql, c.dataSource);
        c.neighbourList = addNeighbourList(c);
        c.neighbourCommands = addNeighbourCommands(c);
        c.replaceView("text/html/get/moviesId");
        return c;
    }

    private ArrayList<String> addNeighbourCommands(Command c) {
        ArrayList<String> res = new ArrayList<>();
        String sql = "SELECT * FROM Sessions " +
                "INNER JOIN Movies ON (Movies._id = Sessions._mid) " +
                "WHERE Sessions._mid = " + c.pathValues.get("movies");
        SessionsDataMapper sessionsDataMapper = new SessionsDataMapper();
        Iterable sessions = sessionsDataMapper.Get(sql, c.dataSource);
        for (Object object : sessions) {
            Sessions session = (Sessions)object;
            res.add("cinemas/" + session.getCid() +
                    "/theaters/" + session.getTid() +
                    "/sessions/" + session.getiD());
        }
        return res;
    }

    private ArrayList<String> addNeighbourList(Command c) {
        ArrayList<String> res = new ArrayList<>();
        String sql = "SELECT * FROM Sessions " +
                "INNER JOIN Movies ON (Movies._id = Sessions._mid) " +
                "WHERE Sessions._mid = " + c.pathValues.get("movies");
        SessionsDataMapper sessionsDataMapper = new SessionsDataMapper();
        Iterable sessions = sessionsDataMapper.Get(sql, c.dataSource);
        for (Object object : sessions) {
            Sessions session = (Sessions)object;
            res.add("cinemas/" + session.getCid());
        }
        return res;
    }
}
