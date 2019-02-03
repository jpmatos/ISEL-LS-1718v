package pt.isel.ls.commands.methods;

import pt.isel.ls.app.CommandProcessor;
import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.annotations.MethodInfo;

import java.util.Scanner;

@MethodInfo(method = "INTERACTIVE") //called when method is empty
public class INTERACTIVE extends AbstractMethod {
    private static final String DESCRIPTION = "Enters Interactive mode.";
    private static final String FINISH_MESSAGE = "Command finished.";

    static final private Scanner user_input = new Scanner(System.in);

    @Override
    protected Command methodExecute(Command c) {
        System.out.println("Entering INTERACTIVE mode.");
        Command command;
        String commandString;
        boolean condition = true;
        do {
            System.out.println("Input command:");
            commandString = user_input.nextLine();
            command = CommandProcessor.process(commandString);
                if (command.method.equals("EXIT")) {
                    condition = false;
                }
        } while (condition);
        c.iterableResult = generateNoTableIterable(FINISH_MESSAGE, this.getClass().getSimpleName());
        return c;
    }

    @Override
    protected String methodGetDescription() {
        return this.getClass().getSimpleName() +": "+ DESCRIPTION;
    }
}
