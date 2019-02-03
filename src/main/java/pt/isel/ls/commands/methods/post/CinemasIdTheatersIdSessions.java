package pt.isel.ls.commands.methods.post;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.SessionsDataMapper;
import pt.isel.ls.tables.Sessions;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/cinemas/var/theaters/var/sessions")
public class CinemasIdTheatersIdSessions extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        SessionsDataMapper sessionsDataMapper = new SessionsDataMapper();
        Sessions session = (Sessions) sessionsDataMapper.Map(c.getPathValuesWithParametersValue(), getHighestID("Sessions", c.dataSource));
        sessionsDataMapper.Insert(session, c.dataSource);
        ArrayList<Sessions> res = new ArrayList<>();
        res.add(session);
        c.iterableResult = res;
        c.replaceView("text/html/post/cinemasIdtheatersId");
        return c;
    }
}
