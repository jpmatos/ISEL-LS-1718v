package pt.isel.ls.utils.exceptions;

public class SessionTimeOverlapException extends Exception {

    @Override
    public void printStackTrace() {
        System.out.println("Current Time Overlaps With Another Session!");
    }
}
