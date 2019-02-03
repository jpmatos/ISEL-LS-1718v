package pt.isel.ls.views;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.annotations.ViewInfo;

@ViewInfo(view = "text/plain")
public class PlainTextView extends AbstractView {

    @Override
    protected String viewParse(Command c) {
        Iterable it = c.iterableResult;
        String res = "";
        for (Object o: it) {
            res += o.toString() + "\n";
        }
        return res;
    }
}
