package pt.isel.ls.views.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.tables.Cinemas;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@ViewInfo(view = "text/html/get/cinemasId")
public class CinemasIdView extends AbstractView {
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
        infoTable.append("<th>Cinema</th>");
        infoTable.append("<th>City</th>");
        infoTable.append("</tr>");
        Cinemas cin = (Cinemas) c.iterableResult.iterator().next();
        infoTable.append("<tr>");
        infoTable.append("<td>").append(cin.getName()).append("</td>");
        infoTable.append("<td>").append(cin.getCity()).append("</td>");
        infoTable.append("</tr>");
        infoTable.append("</table>");
        infoTable.append("<br>");

        StringBuilder theaterTable = new StringBuilder();
        theaterTable.append("<table style=\"width:20%\">");
        theaterTable.append("<tr>");
        theaterTable.append("<th>Theaters</th>");
        theaterTable.append("</tr>");
        for (int i = 0; i < c.neighbourList.size(); i++) {
            String current = c.neighbourList.get(i);
            String[] theaterIDsplit = current.split("/");
            theaterTable.append("<tr>");
            theaterTable.append("<td>");
            theaterTable.append("<a href=\"/").append(current).append("\">").append("Theater-").append(theaterIDsplit[theaterIDsplit.length-1]).append("</a>");
            theaterTable.append("</td>");
            theaterTable.append("</tr>");
        }
        theaterTable.append("</table>");
        theaterTable.append("<br>");

        StringBuilder moviesTable = new StringBuilder();
        moviesTable.append("<table style=\"width:20%\">");
        moviesTable.append("<tr>");
        moviesTable.append("<th>Movies</th>");
        moviesTable.append("</tr>");
        for (int i = 0; i < c.neighbourCommands.size(); i++) {
            String current = c.neighbourCommands.get(i);
            String[] movieIDsplit = current.split("/");
            moviesTable.append("<tr>");
            moviesTable.append("<td><a href=\"/").append(current).append("\">").append("Movie-").append(movieIDsplit[movieIDsplit.length-1]).append("</a></td>");
            moviesTable.append("</tr>");
        }
        moviesTable.append("</table>");
        moviesTable.append("<br>");

        StringBuilder relatedCommands = new StringBuilder();
        relatedCommands.append("<table style=\"width:20%\">");
        relatedCommands.append("<tr>");
        relatedCommands.append("<th>Related Commands</th>");
        relatedCommands.append("</tr>");
        relatedCommands.append("<tr>");
        relatedCommands.append("<td><a href=\"/cinemas\"> Back to Cinemas </a></td>");
        relatedCommands.append("</tr>");
        relatedCommands.append("<tr>");
        relatedCommands.append("<td><a href=\"").append(c.pathWithValues).append("/sessions/date/today").append("\"> Today Sessions for this Cinema </a></td>");
        relatedCommands.append("</tr>");
        relatedCommands.append("</table>");

        StringBuilder insert = new StringBuilder();
        insert.append("<h4>");
        insert.append("Insert new Theater");
        insert.append("</h4>");
        insert.append("<form action=\"").append(c.pathWithValues).append("/theaters").append("\" method=\"post\">");
        insert.append("Theater Name:");
        insert.append("<br>");
        insert.append("<input type=\"text\" name=\"name\">");
        insert.append("<br>");
        insert.append("Seats:");
        insert.append("<br>");
        insert.append("<input type=\"text\" name=\"seats\">");
        insert.append("<br>");
        insert.append("Rows:");
        insert.append("<br>");
        insert.append("<input type=\"text\" name=\"rows\">");
        insert.append("<br>");
        insert.append("<br>");
        insert.append("<input type=\"submit\" value=\"Submit\">");
        insert.append("</form>");

        StringBuilder result = head.append(body.append(homeLink.append(infoTable.append(theaterTable.append(moviesTable.append(relatedCommands.append(insert)))))));
        result.append("</body>");
        result.append("</html>");
        return result.toString();
    }
}
