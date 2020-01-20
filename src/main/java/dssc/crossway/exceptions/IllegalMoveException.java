package dssc.crossway.exceptions;

public class IllegalMoveException extends Exception {

    private final String EXCEPTION_MESSAGE = "Illegal move!";

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
