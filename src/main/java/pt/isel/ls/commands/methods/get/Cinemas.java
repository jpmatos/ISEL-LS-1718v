package pt.isel.ls.commands.methods.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.CinemasDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/cinemas")
public class Cinemas extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Cinemas";
        CinemasDataMapper cinemasDataMapper = new CinemasDataMapper();
        c.iterableResult = cinemasDataMapper.Get(sql, c.dataSource);
        c.neighbourList = addNeighbourCommands(c);
        c.replaceView("text/html/get/cinemas");
        return c;
    }

    private ArrayList<String> addNeighbourCommands(Command c) {
        ArrayList<String> res = new ArrayList<>();
        res.add("");
        for (Object object: c.iterableResult ) {
            pt.isel.ls.tables.Cinemas cinema = (pt.isel.ls.tables.Cinemas) object;
            res.add("cinemas/" + cinema.getiD());
        }
        return res;
    }
}
