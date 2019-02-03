package pt.isel.ls.commands.methods;
import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.annotations.MethodInfo;
import pt.isel.ls.utils.annotations.PathInfo;

@MethodInfo(method = "GET")
public class GET extends AbstractMethod {
    private static final String DESCRIPTION = "Prints/Saves information from SQL server according to pathValues and headers.";

    @Override
    protected Command methodExecute(Command command){
        try {
            AbstractPath get = findPath(command.method.toLowerCase(), command.path);
            if(get != null) {
                return get.execute(command);
            } else throw new NoSuchMethodException();
        } catch (NoSuchMethodException e){
            e.printStackTrace();
            command.responseStatus = 404;
            return command;
        }
    }

    @Override
    protected String methodGetDescription() {
        StringBuilder str = new StringBuilder("GET commands: (" + DESCRIPTION + ")");
        Iterable paths = findPaths("get");
        for (Object object : paths) {
            AbstractPath path = (AbstractPath) object;
            str.append("\n").append("GET ").append(path.getClass().getAnnotation(PathInfo.class).pathMethod());
        }
        return str.toString();
    }
}
