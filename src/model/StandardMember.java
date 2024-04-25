package model;

import model.exception.OverdrawnCreditException;

public class StandardMember extends AbstractMember {

    //Declare instance variables.
    private static final int MAX_CREDIT = 30;
    
    //*** Constructors***
    public StandardMember(String memberId, String memberName) {
        super(memberId, memberName, MAX_CREDIT);
    }
    
    //***Methods***
    //toString - overrides toString method, constructs a string consisting of
    //member id, member name, members remaining credit, plus the STANDARD
    //identifier.
    //string return.
    public String toString() {
        String returnString = this.getMemberId() + ":" + this.getFullName() +
                ":" + this.calculateRemainingCredit() + ":" + "STANDARD"; 
        return returnString;
    }
    
    //returnHolding - allows a standard member to return a holding, as long as
    //they do not have overdrawn credit. Calls the super method returnHolding
    //if this is the case.
    public void returnHolding(Holding holding) 
        throws OverdrawnCreditException { 
        if (super.calculateRemainingCredit() - 
                holding.calculateLateFee(holding.getBorrowDate()) < 0) {  
            throw new OverdrawnCreditException();
        }
        else{
            super.returnHolding(holding);
        }
    }
}
