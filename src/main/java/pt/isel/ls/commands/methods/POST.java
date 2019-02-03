package pt.isel.ls.commands.methods;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.annotations.MethodInfo;
import pt.isel.ls.utils.annotations.PathInfo;

@MethodInfo(method = "POST")
public class POST extends AbstractMethod {
    private static final String DESCRIPTION = "Inserts into SQL server according to pathValues and parametersValue.";

    @Override
    protected Command methodExecute(Command command){
        try {
            AbstractPath get = findPath(command.method.toLowerCase(), command.path);
            return get.execute(command);
        } catch (NoSuchMethodException e){
            e.printStackTrace();
            return command;
        }
    }

    @Override
    protected String methodGetDescription() {
        StringBuilder str = new StringBuilder("POST commands: (" + DESCRIPTION + ")");
        Iterable paths = findPaths("post");
        for (Object object : paths) {
            AbstractPath path = (AbstractPath) object;
            str.append("\n").append("POST ").append(path.getClass().getAnnotation(PathInfo.class).pathMethod());
        }
        return str.toString();
    }
}
