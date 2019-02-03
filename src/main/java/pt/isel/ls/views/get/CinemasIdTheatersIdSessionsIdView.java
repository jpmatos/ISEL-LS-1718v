package pt.isel.ls.views.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.tables.Sessions;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@ViewInfo(view = "text/html/get/cinemasIdtheatersIdsessionsId")
public class CinemasIdTheatersIdSessionsIdView extends AbstractView {
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
        infoTable.append("<th>Date</th>");
        infoTable.append("</tr>");
        Sessions session = (Sessions) c.iterableResult.iterator().next();
        infoTable.append("<tr>");
        infoTable.append("<td>").append(session.getiD()).append("</td>");
        infoTable.append("<td>").append(session.getDate()).append("</td>");
        infoTable.append("</tr>");
        infoTable.append("</table>");
        infoTable.append("<br>");

        StringBuilder ticketsTable = new StringBuilder();
        ticketsTable.append("<table style=\"width:20%\">");
        ticketsTable.append("<tr>");
        ticketsTable.append("<th>Tickets</th>");
        ticketsTable.append("</tr>");
        for (int i = 0; i < c.neighbourList.size(); i++) {
            String current = c.neighbourList.get(i);
            String[] ticketIDsplit = current.split("/");
            ticketsTable.append("<tr>");
            ticketsTable.append("<td><a href=\"/").append(current).append("\">").append("Ticket-").append(ticketIDsplit[ticketIDsplit.length-1]).append("</a></td>");
            ticketsTable.append("</tr>");
        }
        ticketsTable.append("</table>");
        ticketsTable.append("<br>");

        StringBuilder relatedTable = new StringBuilder();
        relatedTable.append("<table style=\"width:20%\">");
        relatedTable.append("<tr>");
        relatedTable.append("<th>Related Links</th>");
        relatedTable.append("</tr>");
        for (int i = 0; i < c.neighbourCommands.size(); i++) {
            String current = c.neighbourCommands.get(i);
            relatedTable.append("<tr>");
            relatedTable.append("<td><a href=\"/").append(current).append("\">/").append(current).append("</a></td>");
            relatedTable.append("</tr>");
        }
        relatedTable.append("</table>");

        StringBuilder insert = new StringBuilder();
        insert.append("<h4>");
        insert.append("Insert new Ticket");
        insert.append("</h4>");
        insert.append("<form action=\"").append(c.pathWithValues).append("/tickets").append("\" method=\"post\">");
        insert.append("Row:");
        insert.append("<br>");
        insert.append("<input type=\"text\" name=\"row\">");
        insert.append("<br>");
        insert.append("Seat:");
        insert.append("<br>");
        insert.append("<input type=\"text\" name=\"seat\">");
        insert.append("<br>");
        insert.append("<br>");
        insert.append("<input type=\"submit\" value=\"Submit\">");
        insert.append("</form>");

        StringBuilder result = head.append(body.append(homeLink.append(infoTable.append(ticketsTable.append(relatedTable.append(insert))))));
        result.append("</body>");
        result.append("</html>");
        return result.toString();
    }
}
