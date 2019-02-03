package pt.isel.ls.views.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.tables.Cinemas;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@ViewInfo(view = "text/html/get/cinemas")
public class CinemasView extends AbstractView {
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

        StringBuilder cinemasTable = new StringBuilder();
        cinemasTable.append("<table style=\"width:50%\">");
        cinemasTable.append("<tr>");
        cinemasTable.append("<th>Cinema</th>");
        cinemasTable.append("<th>City</th>");
        cinemasTable.append("</tr>");
        Iterable it = c.iterableResult;
        for (Object obj:it) {
            Cinemas current = (Cinemas)obj;
            cinemasTable.append("<tr>");
            cinemasTable.append("<td><a href=\"/cinemas/").append(current.getiD()).append("\">").append(current.getName()).append("</a></td>");
            cinemasTable.append("<td>").append(current.getCity()).append("</td>");
            cinemasTable.append("</tr>");
        }
        cinemasTable.append("</table>");

        StringBuilder insert = new StringBuilder();
        insert.append("<h4>Insert new Cinema</h4>");
        insert.append("<form action=\"/cinemas\"").append("method=\"post\">");
        insert.append("Cinema Name:");
        insert.append("<br>");
        insert.append("<input type=\"text\" name=\"name\">");
        insert.append("<br>");
        insert.append("City:");
        insert.append("<br>");
        insert.append("<input type=\"text\" name=\"city\">");
        insert.append("<br>");
        insert.append("<br>");
        insert.append("<input type=\"submit\" value=\"Submit\">");
        insert.append("</form>");

        StringBuilder result = head.append(body.append(homeLink.append(cinemasTable.append(insert))));
        result.append("</body>");
        result.append("</html>");
        return result.toString();
    }
}
