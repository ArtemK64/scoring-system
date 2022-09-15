package scoring.exeptions;

public class WrongEducationInputValueException extends Exception {
    public WrongEducationInputValueException() {
    }

    public WrongEducationInputValueException(String message) {
        super(message);
    }

    public WrongEducationInputValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongEducationInputValueException(Throwable cause) {
        super(cause);
    }

    public WrongEducationInputValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
