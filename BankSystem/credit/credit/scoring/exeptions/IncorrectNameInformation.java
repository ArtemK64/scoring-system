package credit.scoring.exeptions;

public class IncorrectNameInformation extends Exception {
    public IncorrectNameInformation() {
    }

    public IncorrectNameInformation(String message) {
        super(message);
    }

    public IncorrectNameInformation(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectNameInformation(Throwable cause) {
        super(cause);
    }

    public IncorrectNameInformation(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
