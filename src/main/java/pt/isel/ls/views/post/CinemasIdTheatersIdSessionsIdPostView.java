package pt.isel.ls.views.post;

import pt.isel.ls.commands.Command;
import pt.isel.ls.tables.Tickets;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@ViewInfo(view = "text/html/post/cinemasIdtheatersIdSessionsId")
public class CinemasIdTheatersIdSessionsIdPostView extends AbstractView {
    @Override
    protected String viewParse(Command c) {
        Tickets ticket = (Tickets) c.iterableResult.iterator().next();
        c.setResponseStatus(303);
        return "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"refresh\" content=\"0; url=" + c.pathWithValues + "/" + ticket.getId() + "\">" +
                "</head>\n" +
                "</html>";
    }
}
