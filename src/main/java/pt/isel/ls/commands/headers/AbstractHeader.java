package pt.isel.ls.commands.headers;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.interfaces.IHeader;

public abstract class AbstractHeader implements IHeader {
    protected abstract void headerExecute(String value, Command c);

    @Override
    public void execute(String value, Command c) {
        headerExecute(value, c);
    }
}
