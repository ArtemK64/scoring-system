package offers.exceptions;

public class YouAreNotSuitableException extends Exception {
    public YouAreNotSuitableException() {
    }

    public YouAreNotSuitableException(String message) {
        super(message);
    }

    public YouAreNotSuitableException(String message, Throwable cause) {
        super(message, cause);
    }

    public YouAreNotSuitableException(Throwable cause) {
        super(cause);
    }

    public YouAreNotSuitableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
