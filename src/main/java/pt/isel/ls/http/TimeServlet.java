package pt.isel.ls.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.app.CommandProcessor;
import pt.isel.ls.commands.Command;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class TimeServlet extends HttpServlet {

    private static final Logger _logger = LoggerFactory.getLogger(TimeServlet.class);


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        _logger.info("{} on '{}' with accept:'{}', instance {}",
                req.getMethod(), req.getRequestURI(), req.getHeader("Accept"),
                this.hashCode());

        String c = req.getMethod() + " " + req.getRequestURI() + " " + "accept:text/html";
        Command command = CommandProcessor.process(c);

        Charset utf8 = Charset.forName("utf-8");
        resp.setContentType(String.format("text/html; charset=%s", utf8.name()));
        resp.setStatus(command.responseStatus);
            String respBody = command.parsedIterableResult;
            byte[] respBodyBytes = respBody.getBytes(utf8);
            resp.setContentLength(respBodyBytes.length);
            OutputStream os = resp.getOutputStream();
            os.write(respBodyBytes);
            os.close();
    }
}