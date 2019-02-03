package pt.isel.ls.heroku;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HerokuServer {
    private static final int LISTEN_PORT = 8080;

    public static void main(String[] args) throws Exception {
        String portDef = System.getenv("PORT");
        int sysPort = portDef != null ? Integer.valueOf(portDef) : LISTEN_PORT;
        Server server = new Server(sysPort);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(new ServletHolder(new HerokuServlet()), "/*");
        server.start();
        server.join();
    }
}
