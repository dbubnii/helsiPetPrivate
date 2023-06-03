package ua.zorii.helsiPet.exceptions;

public class NotEqualPasswordException extends Exception {
    public NotEqualPasswordException() {
    }

    public NotEqualPasswordException(String message) {
        super(message);
    }
}
