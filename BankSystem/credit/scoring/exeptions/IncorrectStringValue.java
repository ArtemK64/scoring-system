package scoring.exeptions;

public class IncorrectStringValue extends Exception {
    public IncorrectStringValue() {
    }

    public IncorrectStringValue(String message) {
        super(message);
    }

    public IncorrectStringValue(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectStringValue(Throwable cause) {
        super(cause);
    }

    public IncorrectStringValue(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
