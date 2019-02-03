package pt.isel.ls.commands.methods.get.unused;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TicketsDataMapper;
import pt.isel.ls.utils.annotations.PathInfo;

@PathInfo(pathMethod = "/tickets")
public class Tickets extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        String sql = "SELECT * FROM Tickets";
        TicketsDataMapper ticketsDataMapper = new TicketsDataMapper();
        c.iterableResult = ticketsDataMapper.Get(sql, c.dataSource);
        return c;
    }
}
