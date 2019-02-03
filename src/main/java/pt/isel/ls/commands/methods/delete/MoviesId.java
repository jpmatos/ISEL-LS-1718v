package pt.isel.ls.commands.methods.delete;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.MoviesDataMapper;
import pt.isel.ls.tables.Movies;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/movies/var")
public class MoviesId extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        MoviesDataMapper moviesDataMapper = new MoviesDataMapper();
        Movies movie = new Movies(Integer.parseInt(c.pathValues.get("movies")));
        moviesDataMapper.Delete(movie, c.dataSource);
        ArrayList<Movies> res = new ArrayList<>();
        res.add(movie);
        c.iterableResult = res;
        return c;
    }
}
