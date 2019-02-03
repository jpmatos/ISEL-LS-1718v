package pt.isel.ls.app;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0)
            CommandProcessor.process("LISTEN / port=8080");
        else CommandProcessor.process(joinArgs(args));
    }

    private static String joinArgs(String[] args) {
        StringBuilder res = new StringBuilder();
        for (String arg : args) {
            res.append(arg).append(" ");
        }
        res = new StringBuilder(res.substring(0, res.length() - 1));
        return res.toString();
    }
}
