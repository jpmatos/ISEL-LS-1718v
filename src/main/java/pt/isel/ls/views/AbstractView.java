package pt.isel.ls.views;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.interfaces.IView;

public abstract class AbstractView implements IView {
    protected abstract String viewParse(Command c);

    @Override
    public String parse(Command c) {
        return viewParse(c);
    }
}
