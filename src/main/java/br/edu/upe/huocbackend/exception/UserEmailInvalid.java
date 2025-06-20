package br.edu.upe.huocbackend.exception;

public class UserEmailInvalid extends RuntimeException {
    public UserEmailInvalid(String message) {
        super(message);
    }
}
