package pt.isel.ls.commands.methods.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.methods.AbstractPath;
import pt.isel.ls.utils.annotations.PathInfo;

import java.util.ArrayList;

@PathInfo(pathMethod = "")
public class Empty extends AbstractPath {
    @Override
    protected Command pathExecute(Command c) {
        c.iterableResult = new ArrayList<>();
        c.replaceView("text/html/get/home");
        return c;
    }
}
