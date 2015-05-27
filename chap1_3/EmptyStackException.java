package chap1_3;

public class EmptyStackException extends Exception {
    private static final long serialVersionUID = 42L;

    public EmptyStackException() {
        super();
    }

    public EmptyStackException(String message) {
        super(message);
    }

    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyStackException(Throwable cause) {
        super(cause);
    }
}
