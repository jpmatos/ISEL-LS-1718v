package pt.isel.ls.commands.methods.post;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.CinemasDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/cinemas")
public class Cinemas extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        CinemasDataMapper cinemasDataMapper = new CinemasDataMapper();
        pt.isel.ls.tables.Cinemas cinema = (pt.isel.ls.tables.Cinemas) cinemasDataMapper.Map(c.getPathValuesWithParametersValue(), getHighestID("Cinemas", c.dataSource));
        cinemasDataMapper.Insert(cinema, c.dataSource);
        ArrayList<pt.isel.ls.tables.Cinemas> res = new ArrayList<>();
        res.add(cinema);
        c.iterableResult = res;
        //TODO Create a view for POST response
        c.replaceView("text/html/post");
        return c;
    }
}
