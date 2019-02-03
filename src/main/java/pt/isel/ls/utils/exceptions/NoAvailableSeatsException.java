package pt.isel.ls.utils.exceptions;

public class NoAvailableSeatsException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("No more available seats for this Session!");
    }

}
