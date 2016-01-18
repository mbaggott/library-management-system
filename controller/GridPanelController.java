package lms.controller;

import java.awt.event.*;
import javax.swing.*;
import lms.model.Holding;
import lms.model.facade.LMSModel;
import lms.view.GridPanel;
import lms.view.visitor.Visitable;
import lms.view.visitor.Visitor;

//Controller for the GridPanel class. Interacts with model.
public class GridPanelController implements MouseListener, Visitable {

    //Declare instance variables
    private GridPanel panel;
    private LMSModel model;
    private String removeCode;
    Visitor visitor;
    
    // *** Constructor ***
    //Set variables for GridPanel object, Model object, 
    //and the Holding Code to remove.
    public GridPanelController(GridPanel panel, String holdingCode) {
        this.panel = panel;
        this.model = panel.getFrame().getModel();
        this.removeCode = holdingCode;
    }
    
    //Implements required method from Visitor Pattern.
    //Accepts a holding, visits the HoldingVisitor class, and returns the result.
    @Override
    public String accept(Holding holding) {
        return visitor.visit(holding);   
    }
    
    //Implements the mouseClicked method from MouseListener Interface.
    //Requests confirmation to remove Holding from Library Collection, 
    //and from GUI Interface.
    @Override
    public void mouseClicked(MouseEvent me) {
       
        if(me.getButton() == 1) {
           int confirm;
           
           //Executes switch based on Holding type (Book or Video)
           switch(panel.getHoldingType()) {
               
               case "Book":
                   
                   //Request confirmation of removal
                   confirm = JOptionPane.showOptionDialog(panel.getFrame(),
                           "Are you sure you wish to remove this book?",
                           "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                           JOptionPane.QUESTION_MESSAGE, null, null, null);
                   //If request is yes, go ahead and remove
                   if (confirm == 0) {       
                       //Convert code from String to int, 
                       //which removeHolding will accept
                       int code = Integer.parseInt(removeCode);
                       //Remove holding
                       model.removeHolding(code);
                       //Update the ContentPane grid
                       panel.getFrame().getContentPanel().updateGrid(
                               panel.getFrame().getTopMenu().getSortControls()
                               .getSortController().getSortType());
                       //Update the StatusBar
                       panel.getFrame().getStatusBar().getStatusBarController()
                               .updateStatusBar();
                   }
                   break;
                   
               case "Video":
                   
                   //Request confirmation of removal
                   confirm = JOptionPane.showOptionDialog(panel.getFrame(),
                           "Are you sure you wish to remove this video?",
                           "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                           JOptionPane.QUESTION_MESSAGE, null, null, null);
                   //If request is yes, go ahead and remove
                   if (confirm == 0) {  
                       //Convert code from String to int, 
                       //which removeHolding will accept
                       int code = Integer.parseInt(removeCode);
                       //Remove holding
                       model.removeHolding(code);
                       //Update the ContentPane grid
                       panel.getFrame().getContentPanel().updateGrid(
                               panel.getFrame().getTopMenu().getSortControls()
                               .getSortController().getSortType());
                       //Update the StatusBar
                       panel.getFrame().getStatusBar().getStatusBarController()
                               .updateStatusBar();
                   }
                   break;
           }
       }
    }
    
    //Required overridden methods for MouseListener Interface. Not used.
    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {   
    }

    @Override
    public void mousePressed(MouseEvent arg0) {    
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

}
