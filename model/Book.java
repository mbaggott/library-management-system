package lms.model;

import lms.model.util.DateUtil;

public class Book extends AbstractHolding {

    //Declare instance variables.
    private static final int STANDARD_LOAN_FEE = 10;
    private static final int MAXIMUM_LOAN_PERIOD = 28;
    private static final int DAILY_OVERDUE_RATE = 2;
    
    
    //*** Constructors***
    public Book(int code, String title) {
        super(code, title, MAXIMUM_LOAN_PERIOD, STANDARD_LOAN_FEE);
    }
    
    //***Methods***
    //toString - overrides the toString method and constructs a string 
    //consisting of book code, title, default loan fee, maximum loan period, 
    //and the identifier BOOK.
    //returns a string.
    public String toString() {
        String returnString = this.getCode() + ":" + this.getTitle() + ":" + 
                this.getDefaultLoanFee() + ":" + this.getMaxLoanPeriod() +
                ":" + "BOOK"; 
        return returnString;
    }
    
    //calculateLateFee - calculates late fee unique to book holding, based on
    //the number of days loaned for, and the books late fee.
    //returns an integer.
    public int calculateLateFee(String borrowDate) {
        int loanPeriod = this.getMaxLoanPeriod();
        int elapsedDays = DateUtil.getInstance().getElapsedDays(borrowDate);
        int lateFee = 0;
        if (elapsedDays > loanPeriod) {
            lateFee = (elapsedDays - loanPeriod) * DAILY_OVERDUE_RATE;
        }
        return lateFee;
    }
}
