package pt.isel.ls.commands.methods.post;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TheatersDataMapper;
import pt.isel.ls.tables.Theaters;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/cinemas/var/theaters")
public class CinemasIdTheaters extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        TheatersDataMapper theatersDataMapper = new TheatersDataMapper();
        Theaters theater = (Theaters) theatersDataMapper.Map(c.getPathValuesWithParametersValue(), getHighestID("Theaters", c.dataSource));
        theatersDataMapper.Insert(theater, c.dataSource);
        ArrayList<Theaters> res = new ArrayList<>();
        res.add(theater);
        c.iterableResult = res;
        c.replaceView("text/html/post/cinemasId");
        return c;
    }
}
