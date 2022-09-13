package scoring.exeptions;

public class YouAreTooYoungException extends Exception {
    public YouAreTooYoungException() {
    }

    public YouAreTooYoungException(String message) {
        super(message);
    }

    public YouAreTooYoungException(String message, Throwable cause) {
        super(message, cause);
    }

    public YouAreTooYoungException(Throwable cause) {
        super(cause);
    }

    public YouAreTooYoungException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
