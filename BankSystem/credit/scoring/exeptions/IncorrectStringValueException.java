package scoring.exeptions;

public class IncorrectStringValueException extends Exception {
    public IncorrectStringValueException() {
    }

    public IncorrectStringValueException(String message) {
        super(message);
    }

    public IncorrectStringValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectStringValueException(Throwable cause) {
        super(cause);
    }

    public IncorrectStringValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
