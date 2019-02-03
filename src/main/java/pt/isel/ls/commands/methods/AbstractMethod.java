package pt.isel.ls.commands.methods;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.ClassFinder;
import pt.isel.ls.utils.NoTableIterable;
import pt.isel.ls.utils.annotations.MethodInfo;
import pt.isel.ls.utils.annotations.PathInfo;
import pt.isel.ls.utils.interfaces.IMethod;

import java.util.ArrayList;

public abstract class AbstractMethod implements IMethod {
    private static final String MAPPERS_PATH = "pt.isel.ls.mappers";
    private static final String PATHMETHODS_PATH = "pt.isel.ls.commands.pathmethods";
    private static final String METHODS_PATH = "pt.isel.ls.commands.methods";

    protected abstract Command methodExecute(Command c);
    protected abstract String methodGetDescription();

    @Override
    public Command execute(Command c) {return methodExecute(c);}
    @Override
    public String getDescription() {return methodGetDescription();}

    protected AbstractPath findPath(String method, String path) throws NoSuchMethodException {
        return (AbstractPath) ClassFinder.<AbstractPath>findClass(METHODS_PATH + "." + method, PathInfo.class, "pathMethod", path);
    }

    protected Iterable findMethods(){
        return ClassFinder.<AbstractMethod>findClasses(METHODS_PATH, MethodInfo.class);
    }

    protected Iterable findPaths(String packageName){
        return ClassFinder.<AbstractMethod>findClasses(METHODS_PATH + "." + packageName, PathInfo.class);
    }

    protected Iterable<NoTableIterable> generateNoTableIterable (String message, String commandCalled){

        ArrayList<NoTableIterable> res = new ArrayList<>();
        res.add(new NoTableIterable(message, commandCalled));
        return res;
    }
}
