package model;

import model.exception.InsufficientCreditException;
import model.exception.MultipleBorrowingException;
import model.exception.OverdrawnCreditException;

public interface Member {
    
    //Interface implemented by Abstract Member.

    public String getMemberId();
    
    public String getFullName();
    
    public int calculateRemainingCredit();
    
    public HistoryRecord[] getBorrowingHistory();
    
    public HistoryRecord getBorrowingHistory(int holdingId);
    
    public void borrowHolding(Holding holding) throws InsufficientCreditException, MultipleBorrowingException;
    
    public void returnHolding(Holding holding) throws OverdrawnCreditException;
    
    public Holding[] getCurrentHoldings();
    
    public int getMaxCredit();
    
    public void resetCredit();
    
    public int calculateTotalLateFees();
    
    public String toString();
}
