package pt.isel.ls.commands.methods.post;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.MovWebAPI;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.MoviesDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/movies")
public class Movies extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        MoviesDataMapper moviesDataMapper = new MoviesDataMapper();
        MovWebAPI api = new MovWebAPI();
        int id = Integer.parseInt(c.parametersValue.get("id").iterator().next());
        pt.isel.ls.tables.Movies movie = api.getMovie(id);
//        pt.isel.ls.tables.Movies movie = (pt.isel.ls.tables.Movies) moviesDataMapper.Map(c.getPathValuesWithParametersValue(), getHighestID("Movies", c.dataSource));
        moviesDataMapper.Insert(movie, c.dataSource);
        ArrayList<pt.isel.ls.tables.Movies> res = new ArrayList<>();
        res.add(movie);
        c.iterableResult = res;
        c.replaceView("text/html/post/movies");
        return c;
    }
}
