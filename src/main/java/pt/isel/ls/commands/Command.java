package pt.isel.ls.commands;

import org.postgresql.ds.PGSimpleDataSource;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Command {
    private static final String DEFAULT_ACCEPT = "text/plain";
    private static final String DEFAULT_FILENAME = "NO_FILE_SPECIFIED";

    public String method;
    public String path;
    public String pathWithValues;
    public LinkedHashMap<String, String> pathValues;
    public LinkedHashMap<String, String> headers = new LinkedHashMap<>();
    public LinkedHashMap<String, ArrayList<String>> parametersValue;
    public ArrayList<String> pathMethodValues = new ArrayList<>();
    public Iterable iterableResult;
    public String parsedIterableResult;
    public ArrayList<String> neighbourList;
    public int responseStatus = 200;
    public PGSimpleDataSource dataSource;
    public ArrayList<String> neighbourCommands;

    public Command(String components) {
        this.dataSource = null;
        processComponentsString(components);
    }

    public Command(String components, String jdbcUrl) {
        this.dataSource = new PGSimpleDataSource();
        this.dataSource.setUrl(jdbcUrl);
        processComponentsString(components);
    }

    private void processComponentsString(String components) {
        String[] componentsPart = components.split(" ");
        method = componentsPart[0];
        String part;
        if(componentsPart.length>1){
            pathWithValues = componentsPart[1];
            for (int i = 1; i < componentsPart.length; i++) {
                part = identifyPart(componentsPart[i]);
                switch (part){
                    case "PATH":
                        String lastTable = findLastTable(componentsPart[i]);
                        processValuesAndPath(componentsPart[i], lastTable);
                        break;
                    case "HEADERS":
                        headers = processHeaders(componentsPart[i]);
                        break;
                    case "PARAMETERS":
                        parametersValue = processParameters(componentsPart[i]);
                        break;
                }
            }
        }
        if (headers.isEmpty()) headers = addDefaults();
    }

    //proccesses path, pathValues and pathMethodValues
    private void processValuesAndPath(String s, String lastTable) {
        String[] split = s.split("/");
        String str;
        ArrayList<String> pathMethodValuesRes = new ArrayList<>();
        LinkedHashMap<String, String> pathValuesRes = new LinkedHashMap<>();
        if(split.length > 2) {
            int i;
            for (i = 1; i < split.length-1; ++i) {
                if (!isNumeric(split[i]) && isNumeric(split[i + 1]))
                    pathValuesRes.put(split[i], (split[++i]));
                if (split[i].equals(lastTable)) {
                    if (split.length - 1 > i)
                        if (isNumeric(split[i + 1]))
                            i++;
                    break;
                }
            }
            if (split.length - 1 > i) {
                i++;
                int lengthSplit = split.length;
                if (lengthSplit > i) {
                    i++;
                    for (; i < lengthSplit; i++) {
                        pathMethodValuesRes.add(split[i]);
                        if(isNumeric(split[i])) {
                            split[i] = "var";
                        }
                    }
                }
            }
            for (int j = 1; j < split.length; j++) {
                if (isNumeric(split[j])) split[j] = "var";
            }
        }
        pathValues = pathValuesRes;
        pathMethodValues = pathMethodValuesRes;
        path = String.join("/", split);
    }

    //adds the default values to headers
    private static LinkedHashMap<String, String> addDefaults() {
        LinkedHashMap<String, String> res = new LinkedHashMap<>();
        res.put("accept", DEFAULT_ACCEPT);
        res.put("file-name", DEFAULT_FILENAME);
        return res;
    }

    //identifies which part of the command this stirng belongs
    private String identifyPart(String s) {
        if (s.contains("=")){
            return "PARAMETERS";
        } else if (s.contains(":")){
            return "HEADERS";
        } else if (s.contains("/")){
            return "PATH";
        }
        return "NONE";
    }

    //add a value to a linkedhashmap with array list<string> as value
    private static LinkedHashMap<String,ArrayList<String>> addValues(String s, String s1, LinkedHashMap<String, ArrayList<String>> res) {
        ArrayList<String> tempList = null;
        if (res.containsKey(s)) {
            tempList = res.get(s);
            if(tempList == null)
                tempList = new ArrayList<>();
            tempList.add(s1);
        } else {
            tempList = new ArrayList<>();
            tempList.add(s1);
        }
        res.put(s,tempList);
        return res;
    }

    //fills a linkedhashmap with the headers
    private static LinkedHashMap<String, String> processHeaders(String headers){
        String[] split = headers.split("\\|");
        LinkedHashMap<String, String> res = addDefaults();
        for (int i = 0; i < split.length; i++) {
            String[] split2 = split[i].split(":");
            res.put(split2[0], split2[1]);
        }
        return res;
    }

    //fills a linkedhashmap with the parametersValue
    private static LinkedHashMap<String,ArrayList<String>> processParameters(String parameters) {
        String[] split = parameters.split("&");
        LinkedHashMap<String, ArrayList<String>> res = new LinkedHashMap<>();
        for (int i = 0; i < split.length; i++){
            String[] split2 = split[i].split("=");
            res = addValues(split2[0], split2[1].replace("+", " "), res);
        }
        return res;
    }

    //gets the lastTable to insert/get from
    private static String findLastTable(String commandPart) {
        String[] split = commandPart.split("/");
        String table = "";
        boolean lastWasNumeric= true;
        for (String aSplit : split) {
            if (isNumeric(aSplit)){
                lastWasNumeric = true;
            } else {
                if (lastWasNumeric) table = aSplit;
                lastWasNumeric = false;
            }
        }
        return table;
    }

    //checks if string contains only a number
    private static boolean isNumeric(String str) {
        str = str.replace("-", "").replace("+", "");
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    //returns pathValues and parameterValues in the same linkedhashmap
    public LinkedHashMap<String, ArrayList<String>> getPathValuesWithParametersValue() {
        LinkedHashMap<String, ArrayList<String>> res = parametersValue;
        for (Map.Entry<String, String> entry : pathValues.entrySet()) {
            addValues(entry.getKey(), entry.getValue(), res);
        }
        return res;
    }

    //returns pathValues as an string array list for value
    public LinkedHashMap<String, ArrayList<String>> getPathValuesAsStringArrayList() {
        LinkedHashMap<String, ArrayList<String>> res = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : pathValues.entrySet()) {
            addValues(entry.getKey(), entry.getValue(), res);
        }
        return res;
    }

    public void replaceView(String s) {
        this.headers.replace("accept", s);
    }

    public void setResponseStatus(int responseCode) {
        responseStatus = responseCode;
    }
}
