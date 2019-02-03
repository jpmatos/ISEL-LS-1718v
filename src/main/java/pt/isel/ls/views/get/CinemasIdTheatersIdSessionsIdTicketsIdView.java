package pt.isel.ls.views.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.tables.Tickets;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@ViewInfo(view = "text/html/get/cinemasIdtheatersIdsessionsIdticketsId")
public class CinemasIdTheatersIdSessionsIdTicketsIdView extends AbstractView {
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
        infoTable.append("<th>ID</th>");
        infoTable.append("<th>Row</th>");
        infoTable.append("<th>Seat</th>");
        infoTable.append("</tr>");
        Tickets tickets = (Tickets) c.iterableResult.iterator().next();
        infoTable.append("<tr>");
        infoTable.append("<td>").append(tickets.getId()).append("</td>");
        infoTable.append("<td>").append(tickets.getRow()).append("</td>");
        infoTable.append("<td>").append(tickets.getSeat()).append("</td>");
        infoTable.append("</tr>");
        infoTable.append("</table>");
        infoTable.append("<br>");

        StringBuilder relatedTable = new StringBuilder();
        relatedTable.append("<table style=\"width:20%\">");
        relatedTable.append("<tr>");
        relatedTable.append("<th>Related Links</th>");
        relatedTable.append("</tr>");
        relatedTable.append("<tr>");
        relatedTable.append("<td><a href=\"/").append(c.neighbourCommands.get(0)).append("\"> Back to Session </a></td>");
        relatedTable.append("</tr>");
        relatedTable.append("</table>");

        StringBuilder result = head.append(body.append(homeLink.append(infoTable.append(relatedTable))));
        result.append("</body>");
        result.append("</html>");
        return result.toString();
    }
}
