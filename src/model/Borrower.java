package model;

import model.exception.InsufficientCreditException;
import model.exception.MultipleBorrowingException;
import model.exception.OverdrawnCreditException;

public interface Borrower extends Member {
    
    //Interface that extends the Member interface, will be inherited by 
    //AbstractMember
    
    public void borrowHolding(Holding holding) throws 
        InsufficientCreditException, MultipleBorrowingException;
    
    public void returnHolding(Holding holding) throws OverdrawnCreditException;

}
