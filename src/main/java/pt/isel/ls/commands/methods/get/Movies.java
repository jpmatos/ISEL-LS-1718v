package pt.isel.ls.commands.methods.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.MoviesDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/movies")
public class Movies extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Movies";
        MoviesDataMapper moviesDataMapper = new MoviesDataMapper();
        c.iterableResult = moviesDataMapper.Get(sql, c.dataSource);
        c.neighbourList = addNeighbourCommands(c);
        c.replaceView("text/html/get/movies");
        return c;
    }

    private ArrayList<String> addNeighbourCommands(Command c) {
        ArrayList<String> res = new ArrayList<>();
        res.add("");
        for (Object object : c.iterableResult) {
            pt.isel.ls.tables.Movies movie = (pt.isel.ls.tables.Movies) object;
            res.add("movies/" + movie.getiD());
        }
        return res;
    }
}
