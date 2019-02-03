package pt.isel.ls.commands.methods.delete;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.CinemasDataMapper;
import pt.isel.ls.tables.Cinemas;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/cinemas/var")
public class CinemasId extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        CinemasDataMapper cinemasDataMapper = new CinemasDataMapper();
        Cinemas cinema = new Cinemas(Integer.parseInt(c.pathValues.get("cinemas")));
        cinemasDataMapper.Delete(cinema, c.dataSource);
        ArrayList<Cinemas> res = new ArrayList<>();
        res.add(cinema);
        c.iterableResult = res;
        return c;
    }
}
