package pt.isel.ls.views.post;

import pt.isel.ls.commands.Command;
import pt.isel.ls.tables.Cinemas;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@ViewInfo(view = "text/html/post")
public class CinemasPostView extends AbstractView {
    @Override
    protected String viewParse(Command c) {
        Cinemas cinema = (Cinemas) c.iterableResult.iterator().next();
        c.setResponseStatus(303);
        return "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"refresh\" content=\"0; url=" + c.path + "/" + cinema.getiD() + "\">" +
                "</head>\n" +
                "</html>";
    }
}
