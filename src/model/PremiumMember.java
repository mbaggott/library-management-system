package model;

public class PremiumMember extends AbstractMember {

    //Declare instance variables.
    private static final int MAX_CREDIT = 45;
    
    //*** Constructors***
    public PremiumMember(String memberId, String memberName) {
        super(memberId, memberName, MAX_CREDIT);
    }
    
    //***Methods***
    //toString - overrides toString method, constructs a string consisting of
    //member id, member name, members remaining credit, plus the PREMIUM
    //identifier.
    //string return.
    public String toString() {
        String returnString = this.getMemberId() + ":" + this.getFullName() +
                ":" + this.calculateRemainingCredit() + ":" + "PREMIUM"; 
        return returnString;
    } 
}
