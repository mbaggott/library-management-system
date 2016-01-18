package lms.controller;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import lms.view.*;
import lms.view.visitor.*;
import lms.model.facade.*;
import lms.model.*;

//Controller for the TopMenu class. Interacts with model.
public class TopMenuController implements ActionListener, Visitable {

    //Declare instance variables
    private TopMenu topMenu;
    private LMSModel model;
    private String removeBookCode, removeVideoCode;
    private int addBookCode, addVideoCode, addVideoLoanFee;
    private String addBookName, addVideoName;
    Visitor visitor;
    boolean cancelled;
    
    // *** Constructor ***
    //Set variables for TopMenu object, Model object, and default Book/Video codes 
    public TopMenuController(TopMenu topMenu) {
        this.topMenu = topMenu;
        this.model = topMenu.getFrame().getModel();
        this.addBookCode = -1;
        this.addVideoCode = -1;
        
    }
    
    // *** Setters ***
    //Set variables that will be used as validation tools
    public void setRemoveBookCode(String code) {
        this.removeBookCode = code;
    }
    
    public void setRemoveVideoCode(String code) {
        this.removeVideoCode = code;
    }
    
    public void setAddBookCode(int bookCode) {
        this.addBookCode = bookCode;
    }
    
    public void setAddBookName(String bookName) {
        this.addBookName = bookName;
    }
    
    public void setAddVideoCode(int videoCode) {
        this.addVideoCode = videoCode;
    }
    
    public void setAddVideoName(String videoName) {
        this.addVideoName = videoName;
    }
    
    public void setAddVideoLoanFee(int loanFee) {
        this.addVideoLoanFee = loanFee;
    }
    
    public void setCancelled(boolean value) {
        this.cancelled = value;
    }
    
    //Overridden method required by ActionListener Interface.
    //Perform actions depending on Action Command set in TopMenu class.
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        //Execute switch based on Action Command.
        switch(ae.getActionCommand()) {
            
            //Action Command to Exit the program. Will ask for confirmation.
            case "Exit":
                int confirmExit = JOptionPane.showOptionDialog(topMenu.getFrame(),
                        "Are you sure you wish to exit the LMS System?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirmExit == 0) {     
                    System.exit(0);
                }
                break;
            
            //Action Command to initialise library collection
            case "Initialise Library":
                //Execute this condition only if there is NO Library Collection
                if (model.getCollection() == null) {
                    //Allow setting of null Library Code and Name
                    String libCode = null, libName = null;
                    
                    // *** Construct Dialog to request collection details
                    JTextField libCodeField = new JTextField();
                    JTextField libNameField = new JTextField();
                    JPanel libInfoPanel= new JPanel(new GridLayout(0,1));
                    libInfoPanel.add(new JLabel("Library Code"));
                    libInfoPanel.add(libCodeField);
                    libInfoPanel.add(new JLabel("Library Name"));
                    libInfoPanel.add(libNameField);
                    int result = JOptionPane.showConfirmDialog(null, libInfoPanel,
                            "Initialise Library", JOptionPane.OK_CANCEL_OPTION, 
                            JOptionPane.PLAIN_MESSAGE);
                    
                    // If OK is selected (will accept null values for code and name)
                    if (result == JOptionPane.OK_OPTION) {
                        //Get Collection code and name from user input fields
                        libCode = libCodeField.getText();
                        libName = libNameField.getText();
                        //Add collection to the model
                        model.addCollection(new LibraryCollection(libCode, libName));
                        //Set the Library Collection code for the StatusBar class
                        topMenu.getFrame().getStatusBar().setLibCode(libCode);
                        //Initialise Tester data as required by specifications
                        //this should occur each time a 
                        //Library Collection is initialised
                        topMenu.getFrame().getTester().setupTestData(model);
                        //Update ContentPane grid GUI (And statusBar)
                        topMenu.getFrame().getContentPanel().updateGrid(topMenu
                                .getSortControls().getSortController()
                                .getSortType());
                    }
                    
                }
                break;
            
            //Action Command to reset library    
            case "Reset Library":
                //If a Library Collection ALREADY exists
                //Request permission from the user to reset the LibraryCollection 
                if (model.getCollection() != null) {
                    Holding[] holdings = model.getAllHoldings();
                    //Only execute if Collection and getAllHoldings 
                    //are not null, avoids exception
                    if (model.getCollection() != null && model
                            .getAllHoldings() !=  null) {
                        //Request user permission to reset 
                        //Collection via a dialog box
                        int confirmReset = JOptionPane.showOptionDialog(
                                topMenu.getFrame(),
                                "Are you sure you wish to reset the library?",
                                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, null, null);
                        //If request is confirmed
                        if (confirmReset == 0) {     
                            //Remove all Holdings from Collection
                            for (int x = 0; x < holdings.length; x++) {
                                model.removeHolding(holdings[x].getCode());
                            }
                            //Update the contentPane grid GUI 
                            //(Which will also update StausBar)
                            topMenu.getFrame().getContentPanel().updateGrid(
                                    topMenu.getSortControls().getSortController()
                                    .getSortType());
                        }
                    }
                }
                
                break;
                
            //Action Command to add a book to the collection
            case "Add Book":
                //Execute only if a Collection exists
                if (checkCollection()) {
                    //dialog variable is never used, 
                    //but execution of constructor is required
                    @SuppressWarnings("unused")
                    AddBookDialog dialog = new AddBookDialog(this);
                    //Executed only IF user has set a code, code is not blank,
                    //and cancel has not been pressed
                    //Further validation is executed in the Dialog itself
                    if (this.addBookCode != -1 && !this.addBookName.equals("")
                            && this.cancelled == false) {
                        //Create new Book with details provided by user
                        Holding holding = new Book(this.addBookCode, 
                                this.addBookName);
                        //Add the holding it it does not exist, 
                        //otherwise report error
                        if(!model.addHolding(holding)) {
                            JOptionPane.showMessageDialog(null,
                                    "Error! Holding code already exists.");
                        }
                        //Update ContentPane grid GUI (which also updates StatusBar)
                        topMenu.getFrame().getContentPanel().updateGrid(
                                topMenu.getSortControls().getSortController()
                                .getSortType());                       
                    }
                }
                break;
            
            //Action Command to remove a book from the collection
            case "Remove Book":
                //If an existing code exists, set it to null
                removeBookCode = null;
                //Execute only if Library Collection exists
                if (checkCollection()) {
                    //Execute only if there are books currently in the Collection
                    if (model.getAllHoldings() != null && model.countBooks() > 0) {
                        //Get an array of all current Holdings
                        Holding[] listHoldings = model.getAllHoldings();
                        //Construct a Visitor to check for Holding type
                        visitor = new HoldingVisitor();
                        String[] listValues = new String[model.countBooks()];
                        int count = 0;
                        //Traverse the complete list of Holdings
                        for (int x = 0; x < listHoldings.length; x++) {
                            //Take action IF Holding type is Book, and store
                            //Holding Code, and increment total number of Books
                            if (accept(listHoldings[x]).equals("Book")) {
                                Integer code = listHoldings[x].getCode();
                                listValues[count] = code.toString();                        
                                count++;
                            }
                        }
                        //Variable is never used, but 
                        //execution of the constructor is required
                        @SuppressWarnings("unused")
                        RemoveBookDialog removeBook = new RemoveBookDialog(
                                listValues, topMenu.getFrame());
                        //Execute IF the dialog has set a Book Code
                        if (removeBookCode != null) {
                            //Convert String code to Integer, 
                            //so it will work with removeHolding()
                            Integer code = Integer.parseInt(removeBookCode);
                            //Remove Holding
                            model.removeHolding(code);
                            //Update ContentPane grid (and status bar)
                            topMenu.getFrame().getContentPanel().updateGrid(
                                    topMenu.getSortControls().getSortController()
                                    .getSortType());
                        }
                    }
                 
                }
                break;
                
            //Action Command to add a video to the collection
            case "Add Video":
                //Execute only if Library Collection exists
                if (checkCollection()) {
                    //dialog variable is never used, but execution of 
                    //constructor is required
                    @SuppressWarnings("unused")
                    AddVideoDialog dialog = new AddVideoDialog(this);
                    //Executed only IF user has set a code, code is not blank,
                    //and cancel has not been pressed
                    //Further validation is executed in the Dialog itself
                    if (this.addVideoCode != -1 && !this.addVideoName.equals("")
                            && this.addVideoLoanFee != -1
                            && this.cancelled == false) {
                        //Create new Video with details provided by user
                        Holding holding = new Video(this.addVideoCode, 
                                this.addVideoName, this.addVideoLoanFee);
                        //Add the holding it it does not exist,
                        //otherwise report error
                        if(!model.addHolding(holding)) {
                            JOptionPane.showMessageDialog(null, 
                                    "Error! Holding code already exists.");
                        }
                        //Update ContentPane grid GUI (which also updates StatusBar)
                        topMenu.getFrame().getContentPanel().updateGrid(
                                topMenu.getSortControls().getSortController()
                                .getSortType());
                    }
                }
                break;
                
            //Action Command to remove a video from the collection
            case "Remove Video":
                //If an existing code exists, set it to null
                removeVideoCode = null;
                //Execute only if Library Collection exists
                if (checkCollection()) {
                    //Execute only if there are videos currently in the Collection
                    if (model.getAllHoldings() != null && model.countVideos() > 0) {
                        //Get an array of all current Holdings
                        Holding[] listHoldings = model.getAllHoldings();
                        //Construct a Visitor to check for holding type
                        visitor = new HoldingVisitor();
                        String[] listValues = new String[model.countVideos()];
                        int count = 0;
                        //Traverse the complete list of Holdings
                        for (int x = 0; x < listHoldings.length; x++) {
                            //Take action IF Holding type is Book, and store 
                            //Holding Code, and increment total number of Books
                            if (accept(listHoldings[x]).equals("Video")) {
                                Integer code = listHoldings[x].getCode();
                                listValues[count] = code.toString();                        
                                count++;
                            }
                        }
                        
                        //Variable is never used, but 
                        //execution of the constructor is required
                        @SuppressWarnings("unused")
                        RemoveVideoDialog removeBook = new RemoveVideoDialog(
                                listValues, topMenu.getFrame());
                        //Execute IF the dialog has set a Book Code
                        if (removeVideoCode != null) {
                            //Convert String code to Integer, 
                            //so it will work with removeHolding()
                            Integer code = Integer.parseInt(removeVideoCode);
                            //Remove Holding
                            model.removeHolding(code);
                            //Update ContentPane grid (and status bar)
                            topMenu.getFrame().getContentPanel().updateGrid(
                                    topMenu.getSortControls().getSortController()
                                    .getSortType());
                        }
                    }
                 
                }
                break;
                
            //Action Command for Help -> Visitor Pattern Information
            case "Visitor Pattern Information":
                
                //variable is never used, but 
                //execution of the constructor is required
                @SuppressWarnings("unused")
                VisitorHelpDialog dialog = new VisitorHelpDialog(topMenu.getFrame());
                break;
                
            default:
                break;
        }
    }
    
    //Private helper method, checks if Library Collection exists
    private boolean checkCollection() {
        if (model.getCollection() == null) {
            JOptionPane.showMessageDialog(null, "Please initialise the library first!");
            return false;
        }
        return true;
    }

    //Required override for Visitable interface
    @Override
    public String accept(Holding holding) {
        return visitor.visit(holding);   
    }

}
