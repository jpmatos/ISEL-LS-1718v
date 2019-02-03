package pt.isel.ls.views.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.tables.Movies;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@ViewInfo(view = "text/html/get/moviesId")
public class MoviesIdView extends AbstractView {
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
        infoTable.append("<th>Movie</th>");
        infoTable.append("<th>ID</th>");
        infoTable.append("</tr>");
        Movies movie = (Movies) c.iterableResult.iterator().next();
        infoTable.append("<tr>");
        infoTable.append("<td>").append(movie.getTitle()).append("</td>");
        infoTable.append("<td>").append(movie.getiD()).append("</td>");
        infoTable.append("</tr>");
        infoTable.append("</table>");
        infoTable.append("<br>");

        StringBuilder cinemasTable = new StringBuilder();
        cinemasTable.append("<table style=\"width:20%\">");
        cinemasTable.append("<tr>");
        cinemasTable.append("<th>Cinemas</th>");
        cinemasTable.append("</tr>");
        for (int i = 0; i < c.neighbourList.size(); i++) {
            String current = c.neighbourList.get(i);
            String[] cinemasIDsplit = current.split("/");
            cinemasTable.append("<tr>");
            cinemasTable.append("<td>");
            cinemasTable.append("<a href=\"/").append(current).append("\">").append("Cinema-").append(cinemasIDsplit[cinemasIDsplit.length-1]).append("</a>");
            cinemasTable.append("</td>");
            cinemasTable.append("</tr>");
        }
        cinemasTable.append("</table>");
        cinemasTable.append("<br>");

        StringBuilder sessionsTable = new StringBuilder();
        sessionsTable.append("<table style=\"width:20%\">");
        sessionsTable.append("<tr>");
        sessionsTable.append("<th>Sessions</th>");
        sessionsTable.append("</tr>");
        for (int i = 0; i < c.neighbourCommands.size(); i++) {
            String current = c.neighbourCommands.get(i);
            String[] sessionIDsplit = current.split("/");
            sessionsTable.append("<tr>");
            sessionsTable.append("<td>");
            sessionsTable.append("<a href=\"/").append(current).append("\">").append("Session-").append(sessionIDsplit[sessionIDsplit.length-1]).append("</a>");
            sessionsTable.append("</td>");
            sessionsTable.append("</tr>");
        }
        sessionsTable.append("</table>");
        sessionsTable.append("<br>");

        StringBuilder relatedCommands = new StringBuilder();
        relatedCommands.append("<table style=\"width:20%\">");
        relatedCommands.append("<tr>");
        relatedCommands.append("<th>Related Commands</th>");
        relatedCommands.append("</tr>");
        relatedCommands.append("<tr>");
        relatedCommands.append("<td><a href=\"/movies\"> Back to Movies </a></td>");
        relatedCommands.append("</tr>");
        relatedCommands.append("</table>");

        StringBuilder result = head.append(body.append(homeLink.append(infoTable.append(cinemasTable.append(sessionsTable.append(relatedCommands))))));
        result.append("</body>");
        result.append("</html>");
        return result.toString();
    }
}
