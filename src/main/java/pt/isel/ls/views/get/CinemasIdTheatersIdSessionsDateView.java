package pt.isel.ls.views.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.tables.Sessions;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@ViewInfo(view = "text/html/get/cinemasIdtheatersIdsessionsDate")
//TODO Not used for now. (Phase 3)
public class CinemasIdTheatersIdSessionsDateView extends AbstractView {

    @Override
    protected String viewParse(Command c) {

        StringBuilder head = new StringBuilder();
        head.append("<html>");
        head.append("<head>");
        head.append("<style>");
        head.append("table, th, td {border: 1px solid black;}");
        head.append("</style>");
        head.append("</head>");

        StringBuilder body = new StringBuilder();
        body.append("<body>");

        StringBuilder homeLink = new StringBuilder();
        homeLink.append("<h4>");
        homeLink.append("<a href=\"/\">[Home]</a>");
        homeLink.append("</h4>");

        StringBuilder sessionsTable = new StringBuilder();
        Iterable iterable = c.iterableResult;
        sessionsTable.append("<table style=\"width:50%\">");
        sessionsTable.append("<tr>");
        sessionsTable.append("<th>Cid</th>");
        sessionsTable.append("<th>Mid</th>");
        sessionsTable.append("<th>Tid</th>");
        sessionsTable.append("<th>Date</th>");
        sessionsTable.append("</tr>");
        sessionsTable.append("<tr>");
        for (Object obj: iterable) {
            Sessions current = (Sessions)obj;
            sessionsTable.append("<td>").append(current.getCid()).append("</td>");
            sessionsTable.append("<td>").append(current.getMid()).append("</td>");
            sessionsTable.append("<td>").append(current.getTid()).append("</td>");
            sessionsTable.append("<td>").append(current.getDate()).append("</td>");
        }
        sessionsTable.append("</tr>");
        sessionsTable.append("</table>");

        StringBuilder navigaton = new StringBuilder();
        navigaton.append("<p><a href=\"/")
                .append(c.neighbourList.get(c.neighbourList.size()-1))
                .append("\"><=[Prev Day]</a><span style=\"display:inline-block; width: 100;\"></span><a href=\"/")
                .append(c.neighbourList.get(c.neighbourList.size()-2))
                .append("\">[Next Day]=></a></p>\n");
        navigaton.append("<p><a href=\"/").append(c.neighbourList.get(0)).append("\">[Back to cinema]</a></p>");


        StringBuilder result = head.append(body.append(homeLink.append(sessionsTable.append(navigaton))));
        result.append("</body>");
        result.append("</html>");
        return result.toString();
    }
}
