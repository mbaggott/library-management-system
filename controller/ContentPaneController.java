package lms.controller;

import java.util.*;
import lms.view.*;
import lms.view.visitor.HoldingVisitor;
import lms.view.visitor.Visitable;
import lms.model.facade.*;
import lms.model.*;

// Controller for the ContentPane class. Interacts with model.
public class ContentPaneController implements Visitable {
    
    //Declare instance variables
    private ContentPane contentPane;
    private LMSModel model;
    private Map<Integer, GridPanel> panels;
    private HoldingVisitor visitor;
    
    // *** Constructor ***
    //Set variables for ContentPane object and Model object.
    public ContentPaneController(ContentPane contentPane) {
        this.contentPane = contentPane;
        this.model = contentPane.getFrame().getModel();
    }
    
    // *** Getters ***
    // Return the map of GridPanels
    public Map<Integer, GridPanel> getPanels() {
        return this.panels;
    }
    
    //Method to update the Map of GridPanels. Returns the updated map. 
    //Update performed according to sort type.
    public Map<Integer, GridPanel> update(String sortType) {
        if (model.getAllHoldings() != null) {
            switch(sortType) {
                //No sorting
                case "NONE":
                    panels = new HashMap<Integer, GridPanel>();
                    sortNoneCode(); //private helper method
                    break;
                //Sorted by holding code
                case "CODE":
                    panels = new TreeMap<Integer, GridPanel>();
                    sortNoneCode(); //private helper method
                    break;
                //Sorted by holding type
                case "TYPE":
                    panels = new LinkedHashMap<Integer, GridPanel>();
                    sortType(); //private helper method
                    break;
            
            }
            
            return panels;
        }

        //If getallHoldings returns null, create an empty map.
        else {
            panels = new HashMap<Integer, GridPanel>();
            return panels;
        }
        
    }

    //Implements required method from Visitor Pattern.
    //Accepts a holding, visits the HoldingVisitor class, and returns the result.
    @Override
    public String accept(Holding holding) {
        return visitor.visit(holding);   
    }
    
    //Private helper method. Create GridPanels from Holdings in NO order.
    //Uses visitor pattern to determine parameter for GridPanel constructor.
    //Puts the GridPanels in a HashMap (No ordering required).
    private void sortNoneCode() {
        Holding[] holdings = model.getAllHoldings();
        for (int x = 0; x < holdings.length; x++) {
            visitor = new HoldingVisitor();
            GridPanel panel = new GridPanel(contentPane.getFrame(), 
                    holdings[x].getCode(), holdings[x].getTitle(), 
                    holdings[x].getDefaultLoanFee(), 
                    holdings[x].getMaxLoanPeriod(), accept(holdings[x]));
            panels.put(holdings[x].getCode(), panel);
        }
    }
    
    //Private helper method. Create GridPanels from Holdings in TYPE order.
    //Uses visitor pattern to determine parameter for GridPanel constructor.
    //Puts the GridPanels in a LinkedHashMap (Order of insertion is retained).
    //Adds all Book Holdings first, followed by Video Holdings.
    private void sortType() {
        Holding[] holdings = model.getAllHoldings();
        //Create and add all Book Holdings
        for (int x = 0; x < holdings.length; x++) {
            visitor = new HoldingVisitor();
            if (accept(holdings[x]).equals("Book")) {
                GridPanel panel = new GridPanel(contentPane.getFrame(), 
                        holdings[x].getCode(), holdings[x].getTitle(), 
                        holdings[x].getDefaultLoanFee(), 
                        holdings[x].getMaxLoanPeriod(), accept(holdings[x]));
                panels.put(holdings[x].getCode(), panel);
            }
        }
        //Create and add all video holdings
        for (int x = 0; x < holdings.length; x++) {
            visitor = new HoldingVisitor();
            if (accept(holdings[x]).equals("Video")) {
                GridPanel panel = new GridPanel(contentPane.getFrame(), 
                        holdings[x].getCode(), holdings[x].getTitle(), 
                        holdings[x].getDefaultLoanFee(), 
                        holdings[x].getMaxLoanPeriod(), accept(holdings[x]));
                panels.put(holdings[x].getCode(), panel);
            }
        }
    }
    
}
