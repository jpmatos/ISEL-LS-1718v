package pt.isel.ls.commands.methods.delete;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TheatersDataMapper;
import pt.isel.ls.tables.Theaters;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/theaters/var")
public class TheatersId extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        TheatersDataMapper theatersDataMapper = new TheatersDataMapper();
        Theaters theater = new Theaters(Integer.parseInt(c.pathValues.get("theaters")));
        theatersDataMapper.Delete(theater, c.dataSource);
        ArrayList<Theaters> res = new ArrayList<>();
        res.add(theater);
        c.iterableResult = res;
        return c;
    }
}
