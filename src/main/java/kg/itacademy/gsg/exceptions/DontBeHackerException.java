package kg.itacademy.gsg.exceptions;

public class DontBeHackerException extends Exception {
    private static final String message = "Don't be a hacker...";

    public DontBeHackerException() {
        super(message);
    }
}