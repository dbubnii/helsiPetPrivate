package ua.zorii.helsiPet.exceptions;

public class InvalidVerificationCodeException extends Exception {

    public InvalidVerificationCodeException() {
    }

    public InvalidVerificationCodeException(String message) {
        super(message);
    }
}
