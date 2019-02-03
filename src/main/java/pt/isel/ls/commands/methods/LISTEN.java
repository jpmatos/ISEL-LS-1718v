package pt.isel.ls.commands.methods;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import pt.isel.ls.app.CommandProcessor;
import pt.isel.ls.commands.Command;
import pt.isel.ls.heroku.HerokuServlet;
import pt.isel.ls.utils.annotations.MethodInfo;

@MethodInfo(method = "LISTEN")
public class LISTEN extends AbstractMethod{
    private static final String DESCRIPTION = "Starts Jetty server.";
    private static final String FINISH_MESSAGE = "Command finished.";
    @Override
    protected Command methodExecute(Command c) {
        int port = Integer.parseInt(c.parametersValue.get("port").iterator().next());
        Server server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(new ServletHolder(new HerokuServlet()), "/*");
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CommandProcessor.process("INTERACTIVE");
        try {
            server.stop();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.iterableResult = generateNoTableIterable(FINISH_MESSAGE, this.getClass().getSimpleName());
        return c;
    }

    @Override
    protected String methodGetDescription() {
        return this.getClass().getSimpleName() +": "+ DESCRIPTION;
    }
}
