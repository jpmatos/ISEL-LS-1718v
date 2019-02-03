package pt.isel.ls.utils.interfaces;

import pt.isel.ls.commands.Command;

public interface IMethod {
    Command execute(Command c);
    String getDescription();
}
