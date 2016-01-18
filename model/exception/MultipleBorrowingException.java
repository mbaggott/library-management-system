package lms.model.exception;

@SuppressWarnings("serial")
public class MultipleBorrowingException extends LMSException {

    public MultipleBorrowingException() {
        super("Default Multiple Borrowing Exception");
    }

    // NOTE: it is advisable to use this constructor when creating new exceptions
    public MultipleBorrowingException(String message) {
        super(message);
    }

}
