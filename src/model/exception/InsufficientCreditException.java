package model.exception;

@SuppressWarnings("serial")
public class InsufficientCreditException extends LMSException {

    public InsufficientCreditException() {
        super("Default Insufficient Credit Exception");
    }

    // NOTE: it is advisable to use this constructor when creating new exceptions
    public InsufficientCreditException(String message) {
        super(message);
    }
    
}
