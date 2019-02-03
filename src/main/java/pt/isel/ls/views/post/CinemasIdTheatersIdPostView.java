package pt.isel.ls.views.post;

import pt.isel.ls.commands.Command;
import pt.isel.ls.tables.Sessions;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@ViewInfo(view = "text/html/post/cinemasIdtheatersId")
public class CinemasIdTheatersIdPostView extends AbstractView {
    @Override
    protected String viewParse(Command c) {
        Sessions session = (Sessions) c.iterableResult.iterator().next();
        c.setResponseStatus(303);
        return "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"refresh\" content=\"0; url=" + c.pathWithValues + "/" + session.getiD() + "\">" +
                "</head>\n" +
                "</html>";
    }
}
