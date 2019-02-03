package pt.isel.ls.commands.methods.post;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TicketsDataMapper;
import pt.isel.ls.tables.Tickets;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "/sessions/var/tickets")
public class SessionsIdTickets extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        TicketsDataMapper ticketsDataMapper = new TicketsDataMapper();
        Tickets ticket = (Tickets) ticketsDataMapper.Map(c.getPathValuesWithParametersValue(), getHighestID("Tickets", c.dataSource));
        ticketsDataMapper.Insert(ticket, c.dataSource);
        ArrayList<Tickets> res = new ArrayList<>();
        res.add(ticket);
        c.iterableResult = res;
        return c;
    }
}
