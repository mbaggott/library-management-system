package lms.model.exception;

@SuppressWarnings("serial")
public class OverdrawnCreditException extends LMSException {

    public OverdrawnCreditException() {
        super("Default Overdrawn Credit Exception");
    }

    // NOTE: it is advisable to use this constructor when creating new exceptions
    public OverdrawnCreditException(String message) {
        super(message);
    }
    
}
