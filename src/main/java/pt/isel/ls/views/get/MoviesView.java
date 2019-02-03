package pt.isel.ls.views.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.tables.Movies;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@ViewInfo(view = "text/html/get/movies")
public class MoviesView extends AbstractView {
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

        StringBuilder moviesTable = new StringBuilder();
        moviesTable.append("<table style=\"width:50%\">");
        moviesTable.append("<tr>");
        moviesTable.append("<th>Movie</th>");
        moviesTable.append("<th>ID</th>");
        moviesTable.append("</tr>");
        Iterable it = c.iterableResult;
        for (Object obj:it) {
            Movies current = (Movies)obj;
            moviesTable.append("<tr>");
            moviesTable.append("<td><a href=\"/movies/").append(current.getiD()).append("\">").append(current.getTitle()).append("</a></td>");
            moviesTable.append("<td>").append(current.getiD()).append("</td>");
            moviesTable.append("</tr>");
        }
        moviesTable.append("</table>");

        StringBuilder insert = new StringBuilder();
        insert.append("<h4>Insert new Movie</h4>");
        insert.append("<form action=\"/movies\" method=\"post\">");
        insert.append("Movie ID:");
        insert.append("<br>");
        insert.append("<input type=\"text\" name=\"id\">");
        insert.append("<br>");
        insert.append("<br>");
        insert.append("<input type=\"submit\" value=\"Submit\">");
        insert.append("</form>");

        StringBuilder result = head.append(body.append(homeLink.append(moviesTable.append(insert))));
        result.append("</body>");
        result.append("</html>");
        return result.toString();
    }
}
