package model;

import model.util.DateUtil;

public abstract class AbstractHolding implements Holding {

    //Declare instance variables.
    private int code;
    private String title;
    private int loanFee;
    private int maxLoanPeriod;
    private String borrowDate;
    
    //*** Constructors***
    public AbstractHolding() {
        
    }
    
    public AbstractHolding(int code) {
        this.code = code;
    }
    
    public AbstractHolding(int code, String title) {
        this(code);
        this.title = title;
    }
    
    public AbstractHolding(int code, String title, int maxLoanPeriod) {
        this(code, title);
        this.maxLoanPeriod = maxLoanPeriod;
    }
    
    public AbstractHolding(int code, String title, int maxLoanPeriod,
            int loanFee) {
        this(code, title, maxLoanPeriod);
        this.loanFee = loanFee;
    }
    
    //*** Getters***
    public int getCode() {
        return code;
    }
    
    public String getTitle() {
        return title;
    }
    
    public int getDefaultLoanFee() {
        return this.loanFee;
    }
    
    public int getMaxLoanPeriod() {
        return this.maxLoanPeriod;
    }
    
    public String getBorrowDate() {
        return this.borrowDate;
    }
    
    
    //*** Setters***
    //setBorrowDate - set/unset borrowDate
    public void setBorrowDate() {
        if (this.borrowDate == null) {
            this.borrowDate = DateUtil.getInstance().getDate();
        }
        else {
            this.borrowDate = null;
        }
    }
    
    //***Methods***
    //isOnLoan - Check if the holding is on loan.
    //boolean return.
    public boolean isOnLoan() {
        return (this.borrowDate != null);
     }   
}
