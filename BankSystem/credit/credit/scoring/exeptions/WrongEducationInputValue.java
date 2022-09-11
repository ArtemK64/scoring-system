package credit.scoring.exeptions;

public class WrongEducationInputValue extends Exception {
    public WrongEducationInputValue() {
    }

    public WrongEducationInputValue(String message) {
        super(message);
    }

    public WrongEducationInputValue(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongEducationInputValue(Throwable cause) {
        super(cause);
    }

    public WrongEducationInputValue(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
