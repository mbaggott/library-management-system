package lms.model;

import java.util.*;

public class BorrowingHistory {
    
    //Declare instance variables.
    private Map<Integer, HistoryRecord> storedRecords = new HashMap<Integer,
            HistoryRecord>();
   
    //*** Constructors***
    BorrowingHistory() {
    }
   
    //***Methods***
    //addHistoryRecord - creates and adds a new HistoryRecord for the 
    //storedRecords collection.
    public void addHistoryRecord(Holding holding) {
        HistoryRecord history = new HistoryRecord(holding);
        storedRecords.put(holding.getCode(), history);
    }
    
    //calculateTotalLateFees - goes through all the stored records and
    //calculates the sum of the late fees.
    //integer return.
    public int calculateTotalLateFees() {
        int totalLateFees = 0;
        for (HistoryRecord record : storedRecords.values()) {
            totalLateFees += record.getFeePayed() - 
                    record.getHolding().getDefaultLoanFee();
        }
       return totalLateFees;
    }
   
    //getHistoryRecord - gets a specific HistoryRecord.
    //returns HistoryRecord or null.
    public HistoryRecord getHistoryRecord(int holdingId) {
        HistoryRecord record = storedRecords.get(holdingId);
        if(record != null) {
            return record;
        }
        return null;
    }
   
    //getAllHistoryRecords - gets all HistoryRecords from the stored records
    //collection.
    //returns HistoryRecord[] or null.
    public HistoryRecord[] getAllHistoryRecords() {
        HistoryRecord[] history = new HistoryRecord[storedRecords.size()];
        int count = 0;
        for (HistoryRecord record : storedRecords.values()) {
            history[count] = record;
            count++;
        }
        if (count == 0) {
            history = null;
        }
        return history;
    }
}
