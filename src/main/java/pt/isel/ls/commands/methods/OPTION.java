package pt.isel.ls.commands.methods;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.annotations.MethodInfo;

@MethodInfo(method = "OPTION")
public class OPTION extends AbstractMethod {
    private static final String DESCRIPTION = "Prints to console available methods.";
    private static final String FINISH_MESSAGE = "Command finished.";

    @Override
    protected Command methodExecute(Command command) {
        Iterable methods = findMethods();
        for (Object object: methods) {
            AbstractMethod m = (AbstractMethod) object;
            System.out.println(m.methodGetDescription());
        }
        command.iterableResult = generateNoTableIterable(FINISH_MESSAGE, this.getClass().getSimpleName());
        return command;
    }

    @Override
    protected String methodGetDescription() {
        return this.getClass().getSimpleName() + ": " + DESCRIPTION;
    }
}
