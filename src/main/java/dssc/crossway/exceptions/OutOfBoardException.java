package dssc.crossway.exceptions;

public class OutOfBoardException extends Exception {

    private final String EXCEPTION_MESSAGE = "Out of board!";

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
