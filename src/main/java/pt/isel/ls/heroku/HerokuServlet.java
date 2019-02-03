package pt.isel.ls.heroku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.app.CommandProcessor;
import pt.isel.ls.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Map;

public class HerokuServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(HerokuServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {

        logger.info("{} on '{}' (path='{}') with accept:'{}', instance {}",
                req.getMethod(), req.getRequestURI(), req.getPathInfo(), req.getHeader("Accept"),
                this.hashCode());

        String c = req.getMethod() + " " + req.getRequestURI() + " " + "accept:text/html";
        logger.info(c);
        Command command = CommandProcessor.process(c);

        Charset utf8 = Charset.forName("utf-8");
        resp.setContentType(String.format("text/html; charset=%s",utf8.name()));

        try {
            resp.setStatus(command.responseStatus);
            resp.setContentType("text/html");
            OutputStreamWriter wr = new OutputStreamWriter(resp.getOutputStream());
            wr.write(command.parsedIterableResult);
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder parameterStringBuilder = new StringBuilder();
        Map<String, String[]> map = req.getParameterMap();
        for (Map.Entry<String, String[]> entry:map.entrySet()) {
            parameterStringBuilder.append(entry.getKey()).append("=").append(entry.getValue()[0]).append("&");
        }
        String parameterString = parameterStringBuilder.substring(0, parameterStringBuilder.length()-1);
        parameterString = parameterString.replace(" ", "+");
        String c = req.getMethod() + " " + req.getRequestURI() + " " + parameterString;
        Command command = CommandProcessor.process(c);

        Charset utf8 = Charset.forName("utf-8");
        resp.setContentType(String.format("text/html; charset=%s",utf8.name()));

        try {
            resp.setStatus(command.responseStatus);
            resp.setContentType("text/html");
            OutputStreamWriter wr = new OutputStreamWriter(resp.getOutputStream());
            wr.write(command.parsedIterableResult);
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
