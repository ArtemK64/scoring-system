package credit.scoring.exeptions;

public class IncorrectInputIntValues extends Exception {
    public IncorrectInputIntValues() {
    }

    public IncorrectInputIntValues(String message) {
        super(message);
    }

    public IncorrectInputIntValues(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectInputIntValues(Throwable cause) {
        super(cause);
    }

    public IncorrectInputIntValues(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
