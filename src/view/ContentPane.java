package view;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import controller.ContentPaneController;

@SuppressWarnings("serial")
//GUI Class
public class ContentPane extends JPanel {

    //Declare Instance variables
    private ViewFrame frame; 
    private ContentPaneController contentPaneController;
        
    // *** CONSTRUCTOR ***
    //Set Background of panel to white (will show for empty cells)
    //assign frame, and construct and assign controller
    public ContentPane(ViewFrame frame) {
        this.frame = frame;
        contentPaneController = new ContentPaneController(this);
        this.setBackground(Color.WHITE);
    }
    
    // *** GETTERS ***
    public ViewFrame getFrame() {
        return this.frame;
    }
    
    public ContentPaneController getContentPanelController() {
        return this.contentPaneController;
    }
    
    //Updates the gird display with the result of a call to the controller
    public void updateGrid(String sortType) {
        //Remove all components (refresh)
        this.removeAll();
        //Retrieve updated GridPanels
        Map<Integer, GridPanel> panels = contentPaneController.update(sortType);
        //Set the layout (private method)
        setLayout();
        //Execute if any panels are returned
        if  (panels.size() != 0) {
            //Traverse panels in Map
            for (Integer key:panels.keySet()) {
                //Set up a scrollable pane for each panel
                JScrollPane scrollPane = new JScrollPane(panels.get(key));
                //Add a border to the scrollpane, color depends on type of holding
                //The type is determined by the visitor pattern when the 
                //GridPanel is created
                if (panels.get(key).getHoldingType().equals("Book")) {
                    scrollPane.setBorder(BorderFactory.createLineBorder(
                            Color.BLUE, 3));
                }
                else {
                    scrollPane.setBorder(BorderFactory.createLineBorder(
                            Color.RED, 3));
                }
                //Add the component to this panel
                this.add(scrollPane);
            }
            //If there are remaining empty cells, give them gridlines 
            //and set their background color to white
            if (panels.size() % 4 > 0 && panels.size() > 4) {
                int emptyCells = 4 - (panels.size() % 4);
                for (int x = 0; x < emptyCells; x++) {
                    JPanel emptyPanel = new JPanel();
                    emptyPanel.setBackground(Color.WHITE);
                    emptyPanel.setBorder(BorderFactory.createLineBorder(
                            Color.BLACK, 1));
                    //add the empty panels to this JPanel
                    this.add(emptyPanel);
                }
            }
            //Refresh the UI so the new panels are visible
            this.updateUI();
        }
        //If there are no panels, and therefore no holdings in the library, 
        //remove all existing panels and refresh
        else {
            
            this.removeAll();
            this.updateUI();
        }
        //Update the status bar
        frame.getStatusBar().getStatusBarController().updateStatusBar();
    }
    
    //Private helper method to configure layout depending on how many cells there are
    private void setLayout() {        
        int numHoldings = frame.countHoldings();
        int columns = 4;
        if (numHoldings < columns && numHoldings > 0) {
            this.setLayout(new GridLayout(0, numHoldings));
        } else {
            this.setLayout(new GridLayout(0, 4));
        }
        
    }
    
}
