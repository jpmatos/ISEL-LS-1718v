package pt.isel.ls.views.get;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.annotations.ViewInfo;
import pt.isel.ls.views.AbstractView;

import java.lang.reflect.Field;
import java.util.Iterator;

@ViewInfo(view = "text/html")
public class HtmlView extends AbstractView {

    @Override
    protected String viewParse(Command c) {
        //sbh is the stringbuilder object that contains the head of the HTML file while sb contains the tables
        StringBuilder sbh = new StringBuilder("<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "    border: 1px solid black;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n");
        StringBuilder sb = new StringBuilder();

        Iterator it = c.iterableResult.iterator();
        Field[] fields = null;
        Object obj = null;



        while(it.hasNext()){
            obj = it.next();
            fields = obj.getClass().getDeclaredFields();
            sb.append("\t<tr>\n");
            for(Field f : fields){
                sb.append("\t\t<td>");
                f.setAccessible(true);
                try {
                    sb.append(f.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                f.setAccessible(false);
                sb.append("</td>\n");
            }
            sb.append("\t</tr>\n");

        }
//        sb.append("<form action=\"/action_page.php\"><tr>");
//        for (int i = 0; i < fields.length; i++) {
//            sb.append("\t<td>\n");
//
//            sb.append("<input type=\"text\" name="+fields[i].getName()+">");
//            sb.append("</td>\n");
//        }
//        sb.append("</tr>");


        sb.append("</table>\n");
        for(String n:c.neighbourList) {
            sb.append("<p><a href=\"/").append(n).append("\">").append("/").append(n).append("</a></p>\n");   //adding the links to its neighbours
        }
        sb.append("</body>\n" +
                "</html>");

        if (obj!=null) {
            sbh.append("<h3>");
            sbh.append(obj.getClass().getSimpleName());
            sbh.append("</h3>\n" +
                    "<table style=\"width:50%\">\n" +
                    "\t<tr>\n");
            for (Field f : fields) {
                sbh.append("\t\t<th>");
                sbh.append(f.getName());
                sbh.append("</th>\n");
            }
            sbh.append("\t</tr>\n");
        }

        return sbh.toString() + sb.toString();
    }
}
