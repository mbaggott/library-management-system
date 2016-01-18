package lms.model.facade;

import lms.model.exception.InsufficientCreditException;
import lms.model.exception.MultipleBorrowingException;
import lms.model.exception.OverdrawnCreditException;
import lms.model.util.DateUtil;
import lms.model.HistoryRecord;
import lms.model.Holding;
import lms.model.Library;
import lms.model.LibraryCollection;
import lms.model.Member;
import java.util.Observable;

/**
 * @author Mikhail Perepletchikov
 */
public class LMSFacade implements LMSModel {

	/* you need to implement all LMSModel functions here */
    private Library library = new Library();
	
	// this shows an example of using the provided DateUtil class to set the current date
	public void setDate(String currentDate) {
		DateUtil.getInstance().setDate(currentDate);
	}

	 public void addMember(Member member) {
	     library.addMember(member);
	 }

    public void addCollection(LibraryCollection collection) {
        library.addCollection(collection);       
    }
    
    public LibraryCollection getCollection() {
        LibraryCollection collection = library.getCollection();
        return collection;
    }

    public Member getMember() {
        Member member = library.getMember();
        return member;
    }

    public boolean addHolding(Holding holding) {
        if (!library.addHolding(holding)) {
            return false;
        }
        return true;
    }

    public boolean removeHolding(int holdingId) {
        if (!library.removeHolding(holdingId)) {
            return false;
        }
        return true;
    }

    public Holding getHolding(int holdingId) {
        return library.getHolding(holdingId);
    }

    public Holding[] getAllHoldings() {
        return library.getAllHoldings();
    }

    public int countBooks() {
        return library.countBooks();
    }

    public int countVideos() {
        return library.countVideos();
    }
    
    public void borrowHolding(int holdingId)
            throws InsufficientCreditException, MultipleBorrowingException {
                library.borrowHolding(holdingId);
    }

    public void returnHolding(int holdingId) throws OverdrawnCreditException {
        library.returnHolding(holdingId);
        
    }

    public HistoryRecord getHistoryRecord(int holdingId) {
        return library.getHistoryRecord(holdingId);
    }
    
    public HistoryRecord[] getBorrowingHistory() {
        return library.getBorrowingHistory();
    }

    public Holding[] getBorrowedHoldings() {
        return library.getBorrowedHoldings();
    }

    public void resetMemberCredit() {
        library.resetMemberCredit();
        
    }

    public int calculateRemainingCredit() {
        return library.calculateRemainingCredit();
    }

    public int calculateTotalLateFees() {
        return library.calculateTotalLateFees();
    }
}