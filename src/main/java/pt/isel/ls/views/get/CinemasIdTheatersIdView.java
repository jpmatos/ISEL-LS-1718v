package pt.isel.ls.views.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.tables.Theaters;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@ViewInfo(view = "text/html/get/cinemasIdtheatersId")
public class CinemasIdTheatersIdView extends AbstractView {
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

        StringBuilder infoTable = new StringBuilder();
        infoTable.append("<table style=\"width:50%\">");
        infoTable.append("<tr>");
        infoTable.append("<th>Theater</th>");
        infoTable.append("<th>Available Seats</th>");
        infoTable.append("<th>ID</th>");
        infoTable.append("</tr>");
        Theaters theater = (Theaters) c.iterableResult.iterator().next();
        infoTable.append("<tr>");
        infoTable.append("<td>").append(theater.getName()).append("</td>");
        infoTable.append("<td>").append(theater.getAvailableSeats()).append("</td>");
        infoTable.append("<td>").append(theater.getiD()).append("</td>");
        infoTable.append("</tr>");
        infoTable.append("</table>");
        infoTable.append("<br>");

        StringBuilder sessionsTable = new StringBuilder();
        sessionsTable.append("<table style=\"width:20%\">");
        sessionsTable.append("<tr>");
        sessionsTable.append("<th>Session</th>");
        sessionsTable.append("</tr>");
        for (int i = 0; i < c.neighbourList.size(); i++) {
            String current = c.neighbourList.get(i);
            String[] theaterIDsplit = current.split("/");
            sessionsTable.append("<tr>");
            sessionsTable.append("<td><a href=\"/").append(current).append("\">").append("Session-").append(theaterIDsplit[theaterIDsplit.length-1]).append("</a></td>");
            sessionsTable.append("</tr>");
        }
        sessionsTable.append("</table>");
        sessionsTable.append("<br>");

        StringBuilder relatedTable = new StringBuilder();
        relatedTable.append("<table style=\"width:20%\">");
        relatedTable.append("<tr>");
        relatedTable.append("<th>Related Commands</th>");
        relatedTable.append("</tr>");
        relatedTable.append("<tr>");
        relatedTable.append("<td><a href=\"/").append(c.neighbourCommands.get(0)).append("\"> Back to Cinema </a></td>");
        relatedTable.append("</tr>");
        relatedTable.append("</table>");

        StringBuilder insert = new StringBuilder();
        insert.append("<h4>");
        insert.append("Insert new Session");
        insert.append("</h4>");
        insert.append("<form action=\"").append(c.pathWithValues).append("/sessions").append("\" method=\"post\">");
        insert.append("Session date:");
        insert.append("<br>");
        insert.append("<input type=\"text\" name=\"date\">");
        insert.append("<br>");
        insert.append("Movie:");
        insert.append("<br>");
        insert.append("<input type=\"text\" name=\"mid\">");
        insert.append("<br>");
        insert.append("<br>");
        insert.append("<input type=\"submit\" value=\"Submit\">");
        insert.append("</form>");

        StringBuilder result = head.append(body.append(homeLink.append(infoTable.append(sessionsTable.append(relatedTable.append(insert))))));
        result.append("</body>");
        result.append("</html>");
        return result.toString();
    }
}
