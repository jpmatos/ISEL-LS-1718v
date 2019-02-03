package pt.isel.ls.utils.exceptions;

public class UnkwnownMethodException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("Unrecognized method!");
    }
}
