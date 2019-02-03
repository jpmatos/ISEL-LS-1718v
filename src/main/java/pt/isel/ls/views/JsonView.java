package pt.isel.ls.views;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.annotations.ViewInfo;

import java.lang.reflect.Field;
import java.util.Iterator;
@ViewInfo(view = "application/json")
public class JsonView extends AbstractView {

    @Override
    protected String viewParse(Command c){
        StringBuilder sb = new StringBuilder("[\n");
        Iterator it = c.iterableResult.iterator();
        Field[] campos;
        Object obj;

        while(it.hasNext()){
            obj = it.next();
            campos = obj.getClass().getDeclaredFields();
            sb.append("\t{\n");

            for(Field f : campos){
                f.setAccessible(true);
                sb.append("\t\"" + f.getName() + "\": ");
                try {
                    if(f.get(obj) instanceof String) sb.append("\"" + f.get(obj) + "\",\n");
                    else sb.append(f.get(obj) + ",\n");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append("\t},\n");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");

        return sb.toString();
    }


}
