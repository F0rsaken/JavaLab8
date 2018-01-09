package agh.cs.lab8;

public class WrongArgumentsException extends Exception {
    public WrongArgumentsException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Wrong arguments for option -c.";
    }
}
