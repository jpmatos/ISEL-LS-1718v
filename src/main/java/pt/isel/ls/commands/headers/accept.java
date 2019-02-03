package pt.isel.ls.commands.headers;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.ClassFinder;
import pt.isel.ls.utils.annotations.HeaderInfo;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@HeaderInfo(header = "accept")
public class accept extends AbstractHeader {
    private static final String VIEWS_PATH = "pt.isel.ls.views";

    @Override
    protected void headerExecute(String value, Command command) {
            String viewPath = VIEWS_PATH + "." + command.method.toLowerCase();
            AbstractView parser = (AbstractView) ClassFinder.findClass(viewPath, ViewInfo.class, "view", command.headers.get("accept"));
            if(command.iterableResult != null) {
                command.parsedIterableResult = parser.parse(command);
            }
    }
}
