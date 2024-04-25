package model;

import java.util.*;

import model.exception.InsufficientCreditException;
import model.exception.MultipleBorrowingException;
import model.exception.OverdrawnCreditException;

public abstract class AbstractMember implements Member {
    
    //Declare instance variables.
    private String id;
    private String name;
    private int maxCredit;
    private int creditUsed;
    private Map<Integer, Holding> currentHoldings = new HashMap <Integer,
            Holding>();
    private BorrowingHistory history = new BorrowingHistory();
    
    //*** Constructors***
    public AbstractMember() {
    }
    
    public AbstractMember(String id) {
        this.id = id;
        
    }
    
    public AbstractMember(String id, String name) {
        this(id);
        this.name = name;
    }
    
    public AbstractMember(String id, String name, int maxCredit) {
        this(id, name);
        this.maxCredit = maxCredit;
    }
    
    //*** Getters***
    public String getMemberId() {
        return this.id;
    }

    public String getFullName() {
        return this.name;
    }
    
    public int getMaxCredit() {
        return this.maxCredit;
    }
    
    //***Methods***
    //resetCredit - reset member credit to zero.
    public void resetCredit() {
        this.creditUsed = 0;
    }
    
    //calculateRemainingCredit - calculate remaining member credit.
    //integer return.
    public int calculateRemainingCredit() {
        return this. maxCredit - this. creditUsed;
    }
    
    //borrowHolding - allows member to borrowholding IF they have sufficient
    //credit AND if the holding has not been borrowed before.
    //If successful - puts the holding on loan, sets the holdings borrow date,
    //adds holding to collection of members current holdings, adjusts members
    //credit.
    public void borrowHolding(Holding holding)
        throws InsufficientCreditException, MultipleBorrowingException {
            
            if (this.calculateRemainingCredit() < holding.getDefaultLoanFee()) {
                throw new InsufficientCreditException();
            }
            else if (history.getHistoryRecord(holding.getCode()) != null) {
                throw new MultipleBorrowingException();
            }
            else {
                holding.isOnLoan();
                holding.setBorrowDate();
                currentHoldings.put(holding.getCode(), holding);  
                this.creditUsed += holding.getDefaultLoanFee(); 
            }
    }
    
    //returnHolding - Allows the member to return a holding IF they do not
    //become overdrawn.
    //Calculates late fee for holding and adjusts member credit, adds a history
    //record for the holding, unsets the borrow date, removes the holding from
    //the members current holdings collection.
    public void returnHolding(Holding holding) 
            throws OverdrawnCreditException {
                int lateFee = holding.calculateLateFee(holding.getBorrowDate());
                this.creditUsed += lateFee;
                history.addHistoryRecord(holding);
                holding.setBorrowDate();
                currentHoldings.remove(holding.getCode());
    }
    
    //getBorrowingHistory - returns all history records for member.
    //returns HistoryRecord[].
    public HistoryRecord[] getBorrowingHistory() {
        return history.getAllHistoryRecords();
    }
    
    //getBorrowingHistory(holdingid) - returns a specific history record.
    //returns HistoryRecord.
    public HistoryRecord getBorrowingHistory(int holdingId) {
        return history.getHistoryRecord(holdingId);
    }
    
    //getCurrentHoldings - returns all members currently borrowed holdings.
    //returns Holding[] or null.
    public Holding[] getCurrentHoldings() {
        Holding[] holdings = new Holding[currentHoldings.size()];
        int count = 0;
        for (Holding holding : currentHoldings.values()) {
            holdings[count] = holding;
            count++;
        }
        if (count == 0) {
            holdings = null;
        }
        return holdings;
    }
    
    //calculateTotalLateFees - calls the method on BorrowingHistory instance.
    //integer return.
    public int calculateTotalLateFees() {
        return history.calculateTotalLateFees();
    }
}
