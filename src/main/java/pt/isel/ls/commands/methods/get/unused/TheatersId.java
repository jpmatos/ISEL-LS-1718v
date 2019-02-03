package pt.isel.ls.commands.methods.get.unused;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TheatersDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

@PathInfo(pathMethod = "/theaters/var")
public class TheatersId extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Theaters " +
                     "WHERE Theaters._id = " + c.pathValues.get("theaters");;
        TheatersDataMapper theatersDataMapper = new TheatersDataMapper();
        c.iterableResult = theatersDataMapper.Get(sql, c.dataSource);
        return c;
    }
}
