package pt.isel.ls.commands.methods;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.annotations.MethodInfo;

@MethodInfo(method = "EXIT")
public class EXIT extends AbstractMethod {
    private static final String DESCRIPTION = "Exits Interactive mode.";
    private static final String FINISH_MESSAGE = "Command finished.";

    @Override
    protected Command methodExecute(Command command) {
        //TODO check EXIT
        command.iterableResult = generateNoTableIterable(FINISH_MESSAGE, this.getClass().getSimpleName());
        return command;
    }

    @Override
    protected String methodGetDescription() {
        return this.getClass().getSimpleName() + ": " + DESCRIPTION;
    }
}
