package pt.isel.ls.utils.exceptions;

public class InvalidPathMethodParameterException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("Invalid parameter after pathmethod!");
    }
}
