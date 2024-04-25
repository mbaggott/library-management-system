package model;

import java.util.*;

public class LibraryCollection {
    
    //Declare instance variables.
    private String collectionCode;
    private String collectionName;
    private Map<Integer, Holding> holdingsCollection = new HashMap<Integer,
            Holding>();
    
    //*** Constructors***
    public LibraryCollection(String collectionCode, String collectionName) {
        this.collectionCode = collectionCode;
        this.collectionName = collectionName;
    }
    
    //*** Getters***
    public String getCode() {
        return collectionCode;
    }
    
    //***Methods***
    //addHolding - add a holding to the holdings collection if it does not 
    //already exist.
    //boolean return.
    public boolean addHolding(Holding holding) {
        int holdingId = holding.getCode();
        for (Integer key : holdingsCollection.keySet()) {
            if (key == holdingId) {
                return false;
            }
        }
        holdingsCollection.put(holdingId, holding);
        return true;
    }
    
    //getHolding - retrieve a holding from the holdings collection.
    //null or Holding return.
    public Holding getHolding(int holdingId) {
        Holding holding = holdingsCollection.get(holdingId);
        if(holding != null) {
            return holding;
        }
        return null;
    }
    
    //getAllHoldings - get all holdings from the holding collection.
    //Holding[] or null return.
    public Holding[] getAllHoldings() {
        int size = holdingsCollection.size();
        Holding[] allHoldings = new Holding[size];
        int count = 0;
        for (Integer key : holdingsCollection.keySet()) {
            allHoldings[count] = holdingsCollection.get(key);
            count++;
        }
        if (count > 0) {
            return allHoldings;
        }
        return null;
    }
    
    //removeHolding - remove a holding from the holdings collection if it 
    //is not on loan.
    //boolean return.
    public boolean removeHolding(int holdingId) {
        if (holdingsCollection.get(holdingId).isOnLoan() == true) {
                return false;
        }
        holdingsCollection.remove(holdingId);
        return true;
    }
    
    //toString - override the toString method, return a string of collection 
    //code, collection name, and list of holding keys in correct format.
    //string return
    public String toString() {
        String holdingIdString = "";
        Map<Integer, Holding> convertedHoldingsCollection = new TreeMap<Integer,
                Holding>();
        
        //Iterate through Hashmap and convert holdings collection from unordered
        //HashMap to ordered TreeMap.
        for (Map.Entry<Integer, Holding> entry: holdingsCollection.entrySet()) {
            int key = entry.getKey();
            Holding value = entry.getValue();
            convertedHoldingsCollection.put(key, value);
        }
        
        //Iterate through converted holdings collection keys and add to holdings
        //string.
        for (Integer key : convertedHoldingsCollection.keySet()) {
            holdingIdString += key + ",";
        }
        //Remove the last comma in the holdings string
        if (holdingIdString != "") {
            holdingIdString = holdingIdString.substring(0, 
                    holdingIdString.length()-1);
        }
        //Add collection code and name to string, then return
        String returnString = this.collectionCode + ":" + this.collectionName 
                + ":" + holdingIdString;
        return returnString;   
    
    }
    
}
