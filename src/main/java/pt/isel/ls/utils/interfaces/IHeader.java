package pt.isel.ls.utils.interfaces;

import pt.isel.ls.commands.Command;

public interface IHeader {
    void execute(String value, Command c) throws NoSuchMethodException;
}
