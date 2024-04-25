package model;

import model.exception.InsufficientCreditException;
import model.exception.MultipleBorrowingException;
import model.exception.OverdrawnCreditException;

public class Library {

    //Declare instance variables.
    private Member member;
    private LibraryCollection collection;

    //*** Constructors***
    public void addMember(Member member) {
        this.member = member;
    }
    
    //*** Getters***
    public Member getMember() {
        return this.member;
    }
    
    public LibraryCollection getCollection() {
        return this.collection;
    }
    
    //***Methods***
    //addCollection - adds a new collection to the Library.
    public void addCollection(LibraryCollection collection) {
        this.collection = collection;
    }
    
    //addHolding - adds a new Holding to the LibraryCollection.
    //returns a boolean value.
    public boolean addHolding(Holding holding) {
        if (!collection.addHolding(holding)) {
            return false;
        }
        return true;
    }
    
    //removeHolding - removes a specific holding from the LibraryCollection.
    //returns a boolean value.
    public boolean removeHolding(int holdingId) {
        if(!collection.removeHolding(holdingId)) {
            return false;
        }
        return true;
    }
    
    //getholding - gets and returns a holding from the LibraryCollection.
    //returns a Holding.
    public Holding getHolding(int holdingId) {
        return collection.getHolding(holdingId);
    }
    
    //getAllHoldings - gets and returns all holdings from the LibraryCollection.
    //returns a Holding[].
    public Holding[] getAllHoldings() {
        return collection.getAllHoldings();
    }
    
    //countBooks - counts the total number of books in the library collection.
    //integer return.
    public int countBooks() {
        int count = 0;
        if (collection.getAllHoldings() != null) {
            Holding[] allHoldings = collection.getAllHoldings();
            for (int x = 0; x < allHoldings.length; x++) {
                if (allHoldings[x] instanceof Book) {
                    count++;
                }
            }
            return count;
        }
        return count;
    }
    
    //countVideos - counts the total number of videos in the library collection.
    //integer return.
    public int countVideos() {
        int count = 0;
        if (collection.getAllHoldings() != null) {
            Holding[] allHoldings = collection.getAllHoldings();
            for (int x = 0; x < allHoldings.length; x++) {
                if (allHoldings[x] instanceof Video) {
                    count++;
                }
            }
            return count;
        }
        return count;
    }
    
    //borrowHolding - gets the requested holding from the LibraryCollection 
    //and passes it to method of the same name in Member class to borrow 
    //the specified holding.
    public void borrowHolding(int holdingId)
        throws InsufficientCreditException, MultipleBorrowingException {
            Holding holding = collection.getHolding(holdingId);
            member.borrowHolding(holding);
    }
    
    //returnHolding - gets the requested holding from the LibraryCollection 
    //and passes it to method of the same name in Member class to return the 
    //specified holding.
    public void returnHolding(int holdingId) 
            throws OverdrawnCreditException {
        Holding holding = collection.getHolding(holdingId);
        member.returnHolding(holding);
    }
    
    //getHistoryRecord - passes holdingId to member method to obtain a specific
    //HistoryRecord.
    //returns a HistoryRecord.
    public HistoryRecord getHistoryRecord(int holdingId) {
        return member.getBorrowingHistory(holdingId);
        
    }
    
    //getBorrowingHistory - calls method of the same name from the member class
    //to retrieve the members borrowing history.
    //Returns a HistoryRecord[].
    public HistoryRecord[] getBorrowingHistory() {
        return member.getBorrowingHistory();
    }
    
    //getBorrowedHoldings - requests an array of the members currently 
    //borrowed holdings from the Member class.
    //returns a Holding[].
    public Holding[] getBorrowedHoldings() {
        return member.getCurrentHoldings();
    }
    
    //resetMemberCredit - calls method of the same name on the Member class to
    //reset the members credit.
    public void resetMemberCredit() {
        member.resetCredit();
    }
    
    //calculateRemainignCredit - callse method of the same name on the Member
    //class to retrieve the members remaining credit.
    //integer return.
    public int calculateRemainingCredit() {
        return member.calculateRemainingCredit(); 
    }
    
    //calculateTotalLateFees - calls a method of the same name in the member
    //class that returns the total late fees of the member.
    //integer return.
    public int calculateTotalLateFees() {
        return member.calculateTotalLateFees();
    }
}
