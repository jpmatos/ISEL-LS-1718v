package pt.isel.ls.commands.methods.get.unused;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.SessionsDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

@PathInfo(pathMethod = "/cinemas/var/sessions/var")
public class CinemasIdSessionsId extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Sessions " +
                "INNER JOIN Cinemas ON (Cinemas._id = Sessions._cid) " +
                "WHERE Sessions._cid = " + c.pathValues.get("cinemas") +
                " AND Sessions._id = " + c.pathValues.get("sessions");
        SessionsDataMapper sessionsDataMapper = new SessionsDataMapper();
        c.iterableResult = sessionsDataMapper.Get(sql, c.dataSource);
        return c;
    }
}
