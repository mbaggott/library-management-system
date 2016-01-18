package lms.model;

import lms.model.exception.InsufficientCreditException;
import lms.model.exception.MultipleBorrowingException;
import lms.model.exception.OverdrawnCreditException;

public interface Borrower extends Member {
    
    //Interface that extends the Member interface, will be inherited by 
    //AbstractMember
    
    public void borrowHolding(Holding holding) throws 
        InsufficientCreditException, MultipleBorrowingException;
    
    public void returnHolding(Holding holding) throws OverdrawnCreditException;

}
