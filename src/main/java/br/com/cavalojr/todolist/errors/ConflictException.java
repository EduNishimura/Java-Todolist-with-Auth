package br.com.cavalojr.todolist.errors;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
