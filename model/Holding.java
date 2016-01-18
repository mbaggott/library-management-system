package lms.model;

public interface Holding {

    //Interface implemented by AbstractHolding
    
    public int calculateLateFee(String borrowDate);
    
    public String getBorrowDate();
    
    public int getCode();
    
    public int getDefaultLoanFee();
    
    public int getMaxLoanPeriod();
    
    public String getTitle();
    
    public boolean isOnLoan();
    
    public void setBorrowDate();

    public String toString();
}
