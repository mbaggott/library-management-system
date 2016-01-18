package lms.view;

import javax.swing.*;
import lms.controller.TopMenuController;
import java.awt.event.*;

@SuppressWarnings("serial")
//GUI Class
public class TopMenu extends JPanel {
    
    //Declare instance variables
    private ViewFrame frame;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private IconBar iconBar;
    private SortControls sortControls;
    private TopMenuController controller;
        
    // *** CONSTRUCTOR ***
    
    public TopMenu(ViewFrame frame) {
        //Assign frame
        this.frame = frame;
        //Assign variables and controller        
        menuBar = new JMenuBar();
        sortControls = new SortControls(this);
        iconBar = new IconBar(frame);
        controller = new TopMenuController(this);
        //Set layout
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
                
        //Run private helper method for each menu
        fileMenu();
        libraryMenu();
        holdingsMenu();
        helpMenu();
        
        //Set the JMenuBAr to the Frame
        frame.setJMenuBar(menuBar);
        //Add TopMenu components to this JPanel
        this.add(iconBar);
        this.add(Box.createHorizontalGlue()); //right align sort controls 
        this.add(sortControls);
    }
    
    // *** GETTERS ***
    public ViewFrame getFrame() {
        return this.frame;
    }
    
    public TopMenuController getController() {
        return this.controller;
    }
    
    public SortControls getSortControls() {
        return this.sortControls;
    }
    
    public IconBar getIconBar() {
        return this.iconBar;
    }
    
    // Private helper methods
    //File Menu
    private void fileMenu() {  
        //Build the File menu, add accessibility values
        menu = new JMenu("File");
        menu.getAccessibleContext().setAccessibleDescription("File Menu");
        menu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu);
        
        //Build the Exit option, add accessibility values, add listener
        menuItem = new JMenuItem("Exit");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Exit");
        menuItem.addActionListener(controller);
        menu.add(menuItem);
    }
    
    //Library Menu
    private void libraryMenu() {  
        //Build the Library menu, add accessibility values
        menu = new JMenu("Library");
        menu.getAccessibleContext().setAccessibleDescription("File Menu");
        menu.setMnemonic(KeyEvent.VK_L);
        menuBar.add(menu);
        
        //Build the Initialise Library option, add accessibility values, add listener
        menuItem = new JMenuItem("Initialise Library");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Initialise Library");
        menuItem.addActionListener(controller);
        menu.add(menuItem);
        
        //Build the Reset Library option, add accessibility values, add listener
        menuItem = new JMenuItem("Reset Library");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Reset Library");
        menuItem.addActionListener(controller);
        menu.add(menuItem);
    }
    
    //Holdings Menu
    private void holdingsMenu() {  
        //Build the Holdings menu, add accessibility values
        menu = new JMenu("Holdings");
        menu.getAccessibleContext().setAccessibleDescription("Holdings Menu");
        menu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(menu);
        
        //Build the Add Book option, add accessibility values, add listener
        menuItem = new JMenuItem("Add Book");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_B, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Add Book");
        menuItem.addActionListener(controller);
        menu.add(menuItem);
        
        //Build the Remove Book option, add accessibility values, add listener
        menuItem = new JMenuItem("Remove Book");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_K, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Remove Book");
        menuItem.addActionListener(controller);
        menu.add(menuItem);
        
        //Build the Add Video option, add accessibility values, add listener
        menuItem = new JMenuItem("Add Video");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Add Video");
        menuItem.addActionListener(controller);
        menu.add(menuItem);
        
        //Build the Remove Video option, add accessibility values, add listener
        menuItem = new JMenuItem("Remove Video");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Remove Video");
        menuItem.addActionListener(controller);
        menu.add(menuItem);
        
    }
    
    //Help Menu
    private void helpMenu() {  
        //Build the Help menu, add accessibility values
        menu = new JMenu("Help");
        menu.getAccessibleContext().setAccessibleDescription("Help Menu");
        menu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(menu);
        
        //Build the Visitor Pattern Information option, 
        //add accessibility values, add listener
        menuItem = new JMenuItem("Visitor Pattern Information");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Visitor Pattern Information");
        menuItem.addActionListener(controller);
        menu.add(menuItem);
    }
    
}
