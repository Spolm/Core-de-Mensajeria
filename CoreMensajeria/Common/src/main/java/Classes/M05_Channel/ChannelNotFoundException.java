package Classes.M05_Channel;

public class ChannelNotFoundException extends Exception {
    public ChannelNotFoundException() {
    }

    public ChannelNotFoundException(String message) {
        super(message);
    }

    public ChannelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChannelNotFoundException(Throwable cause) {
        super(cause);
    }

    public ChannelNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
