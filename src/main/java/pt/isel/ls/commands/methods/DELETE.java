package pt.isel.ls.commands.methods;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.annotations.MethodInfo;
import pt.isel.ls.utils.annotations.PathInfo;

@MethodInfo(method = "DELETE")
public class DELETE extends AbstractMethod {
    private static final String DESCRIPTION = "Deletes from SQL server according to pathValues and parametersValue.";
    private static final String FINISH_MESSAGE = "Command finished.";

    @Override
    protected Command methodExecute(Command command) {
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
        //return this.getClass().getSimpleName() + " {pathValues} {parametersValue}: " + DESCRIPTION;
        StringBuilder str = new StringBuilder("DELETE commands: (" + DESCRIPTION + ")");
        Iterable paths = findPaths("delete");
        for (Object object : paths) {
            AbstractPath path = (AbstractPath) object;
            str.append("\n").append("DELETE ").append(path.getClass().getAnnotation(PathInfo.class).pathMethod());
        }
        return str.toString();
    }
}
