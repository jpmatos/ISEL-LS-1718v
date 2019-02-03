package pt.isel.ls.commands.methods.get.unused;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TheatersDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

@PathInfo(pathMethod = "/cinemas/var/theaters")
public class CinemasIdTheaters extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Theaters " +
                "INNER JOIN Cinemas ON (Cinemas._id = Theaters._cid) " +
                "WHERE Cinemas._id = " + c.pathValues.get("cinemas");
        TheatersDataMapper theatersDataMapper = new TheatersDataMapper();
        c.iterableResult = theatersDataMapper.Get(sql, c.dataSource);
        return c;
    }
}
