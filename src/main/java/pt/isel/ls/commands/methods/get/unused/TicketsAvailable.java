package pt.isel.ls.commands.methods.get.unused;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.utils.annotations.PathInfo;

@PathInfo(pathMethod = "/tickets/available")
public class TicketsAvailable extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        return null;
    }
}
