package pt.isel.ls.commands.methods.delete;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.mappers.TicketsDataMapper;
import pt.isel.ls.tables.Tickets;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.Arrays;

@PathInfo(pathMethod = "/cinemas/var/theaters/var/sessions/var/tickets")
public class CinemasIdTheatersIdSessionsIdTickets extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        TicketsDataMapper ticketsDataMapper = new TicketsDataMapper();
        Tickets[] tickets = (Tickets[]) ticketsDataMapper.MapArray(c.getPathValuesWithParametersValue(), 0);
        ticketsDataMapper.DeleteArrayWithoutId(tickets, c.dataSource);
        c.iterableResult = Arrays.asList(tickets);
        return c;
    }
}
