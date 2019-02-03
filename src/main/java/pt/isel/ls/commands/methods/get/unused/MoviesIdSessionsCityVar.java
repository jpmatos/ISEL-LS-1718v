package pt.isel.ls.commands.methods.get.unused;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.SessionsDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.Iterator;

@PathInfo(pathMethod = "/movies/var/sessions/city/var")
public class MoviesIdSessionsCityVar extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        Iterator<String> pathMethodValue = c.pathMethodValues.iterator();
        String sql = "SELECT * FROM Sessions " +
                "INNER JOIN Cinemas ON (Cinemas._id = Sessions._cid) " +
                "INNER JOIN Movies ON (Movies._id = Sessions._mid) " +
                "WHERE Sessions._mid = " + c.pathValues.get("movies") +
                " AND Cinemas._city = '" + pathMethodValue.next() + "'";
        SessionsDataMapper sessionsDataMapper = new SessionsDataMapper();
        c.iterableResult = sessionsDataMapper.Get(sql, c.dataSource);
        return c;
    }
}
