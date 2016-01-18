package lms.controller;

import java.awt.event.*;
import lms.model.facade.LMSModel;
import lms.view.SortControls;

//Controller for the RemoveVideo class.
public class SortController implements ActionListener {

    //Declare instance variables
    private SortControls sortControls;
    private LMSModel model;
    private String sortType;
    
    // *** CONSTRUCTOR ***
    // Initialise instance variables
    public SortController(SortControls sortControls) {
        this.sortControls = sortControls;
        this.model = sortControls.getTopMenu().getFrame().getModel();
        this.sortType = "NONE";
    }
    
    // *** GETTERS ***
    public String getSortType() {
        return this.sortType;
    }
    
    //Override required method for ActionListener Interface
    @Override
    //Updates the ContentPane grid, with the currently set sort type,
    //or the default of NONE 
    public void actionPerformed(ActionEvent ae) {
        this.sortType = ae.getActionCommand();
        if (model.getCollection() != null) {
            sortControls.getTopMenu().getFrame().getContentPanel()
                    .updateGrid(this.sortType);
        }
    }

}
