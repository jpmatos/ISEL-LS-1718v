package pt.isel.ls.app;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.headers.AbstractHeader;
import pt.isel.ls.commands.methods.AbstractMethod;
import pt.isel.ls.utils.ClassFinder;
import pt.isel.ls.utils.annotations.HeaderInfo;
import pt.isel.ls.utils.annotations.MethodInfo;
import pt.isel.ls.utils.exceptions.UnkwnownMethodException;

import java.util.Map;

public class CommandProcessor {

    private static final String METHOD_PACKAGE_PATH = "pt.isel.ls.commands.methods.";
    private static final String HEADERS_PATH = "pt.isel.ls.commands.headers";
    //private static final String JDBC_URL = "jdbc:postgresql://ec2-54-217-208-52.eu-west-1.compute.amazonaws.com/da5d0bnvaupbar?user=xzukmodrpdcyga&password=5943284d8bd60d89e6d44d58b42ac775af80ebac266c5de99a16c4bec697831e&ssl=true";
    private static final String JDBC_URL = "jdbc:postgresql://localhost/postgres?user=postgres&password=sqlsetup!123";

    public static Command process(String c) {
        String jdbcURL = System.getenv("JDBC_DATABASE_URL");
        jdbcURL = jdbcURL != null ? jdbcURL : JDBC_URL;
        Command command = new Command(c, jdbcURL);
        try {
            processMethod(command);
            processHeaders(command);
        } catch ( UnkwnownMethodException e) {
            e.printStackTrace();
            command.responseStatus = 404;
        }
        return command;
    }

    private static void processMethod(Command command) throws UnkwnownMethodException {
        AbstractMethod com = (AbstractMethod)ClassFinder.findClass(METHOD_PACKAGE_PATH, MethodInfo.class, "method", command.method);
        if (com != null) {
            com.execute(command);
        } else throw new UnkwnownMethodException();
    }

    private static void processHeaders(Command command) {
        AbstractHeader header;
        for (Map.Entry<String, String> entry : command.headers.entrySet()) {
            header = (AbstractHeader)ClassFinder.findClass(HEADERS_PATH, HeaderInfo.class, "header", entry.getKey());
            header.execute(entry.getValue(), command);
        }
    }
}