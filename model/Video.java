package lms.model;

import lms.model.util.DateUtil;

public class Video extends AbstractHolding {

    //Declare instance variables.
    private static final int MAXIMUM_LOAN_PERIOD = 7;  
    private int loanFee;
    
    //*** Constructors***
    public Video(int code, String title, int loanFee) {
        super(code, title, MAXIMUM_LOAN_PERIOD);
        this.loanFee = loanFee;
    }
    
    //getDefaultLoanFee - overrides the AbstractMember method of the same name
    //and gets the default loan fee for the video.
    //integer return.
    public int getDefaultLoanFee() {
        return this.loanFee;
    }
    
    //toString - overrides the toString method and constructs a string 
    //consisting of book code, title, default loan fee, maximum loan period, 
    //and the identifier VIDEO.
    //returns a string.
    public String toString() {
        String returnString = this.getCode() + ":" + this.getTitle() + ":" + this.getDefaultLoanFee() + ":" + this.getMaxLoanPeriod() + ":" + "VIDEO"; 
        return returnString;
    }
    
    //calculateLateFee - calculates late fee unique to video holding, based on
    //the number of days loaned for, and the books late fee.
    //returns an integer.
    public int calculateLateFee(String borrowDate) {
        int loanPeriod = this.getMaxLoanPeriod();
        int elapsedDays = DateUtil.getInstance().getElapsedDays(borrowDate);
        int lateFee = 0;
        if (elapsedDays > loanPeriod) {
            lateFee = (elapsedDays - loanPeriod) * (this.loanFee/2);
        }
        return lateFee;
    }
}
