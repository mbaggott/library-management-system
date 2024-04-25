package model;

public class HistoryRecord {

    //Declare instance variables.
    private Holding holding;
    private int feePayed;
            
    //*** Constructors***
    public HistoryRecord() {
        
    }
    
    public HistoryRecord(Holding holding) {
        this.holding = holding;
        this.feePayed = holding.getDefaultLoanFee() + holding.calculateLateFee(holding.getBorrowDate());
    }
    
    //*** Getters***
    public Holding getHolding() {
        return this.holding;
    }
    
    public int getFeePayed() {
        return feePayed;  
    }
    
    //***Methods***
    //toString - constructs a string consisting of code, and fee payed.
    //returns a string.
    public String toString() {
        String returnString = this.holding.getCode() + ":" + this.getFeePayed();
        return returnString;
    }
}
