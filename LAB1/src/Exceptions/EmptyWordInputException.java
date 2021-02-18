package Exceptions;

public class EmptyWordInputException extends Exception {
    public  EmptyWordInputException() {
        super();
    }
    public  EmptyWordInputException(String message) {
        super(message);
    }
}
