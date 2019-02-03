package pt.isel.ls.commands.methods.delete;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.SessionsDataMapper;
import pt.isel.ls.tables.Sessions;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/sessions/var")
public class SessionsId extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        SessionsDataMapper sessionsDataMapper = new SessionsDataMapper();
        Sessions session = new Sessions(Integer.parseInt(c.pathValues.get("sessions")));
        sessionsDataMapper.Delete(session, c.dataSource);
        ArrayList<Sessions> res = new ArrayList<>();
        res.add(session);
        c.iterableResult = res;
        return c;
    }
}
