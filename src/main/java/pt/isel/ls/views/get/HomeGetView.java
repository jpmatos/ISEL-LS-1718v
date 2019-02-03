package pt.isel.ls.views.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

@ViewInfo(view = "text/html/get/home")
public class HomeGetView extends AbstractView {
    @Override
    protected String viewParse(Command c) {
        StringBuilder head = new StringBuilder();
        head.append("<html>");
        head.append("<head>");
        head.append("<style>");
        head.append("table, th, td {border: 1px solid black;}");
        head.append("</style>");
        head.append("</head>");

        StringBuilder message = new StringBuilder();
        message.append("<h4>Welcome to our WebPage!</h4>");

        StringBuilder cinema = new StringBuilder();
        cinema.append("<th><a href=\"/cinemas\"> Cinemas </a></th>");
        cinema.append("<br>");
        cinema.append("<br>");

        StringBuilder movies = new StringBuilder();
        movies.append("<th><a href=\"/movies\"> Movies </a></th>");

        StringBuilder result = head.append(message.append(cinema.append(movies)));
        result.append("</body>");
        result.append("</html>");
        return result.toString();
    }
}
