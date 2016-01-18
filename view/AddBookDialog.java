package lms.view;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import lms.controller.TopMenuController;
import lms.controller.IconBarController;
import javax.swing.text.NumberFormatter;

@SuppressWarnings("serial")
//GUI Class
public class AddBookDialog extends JPanel {

    //Declare instance variables
    private TopMenuController topMenuController;
    private IconBarController iconBarController;
    private JFormattedTextField bookCodeField;
    private JTextField bookNameField;
    private String bookName;
    private int bookCode;
    
    // *** CONSTRUCTORS ***
    
    // Constructor called by TopMenuController 
    //Initialise controllers, and call private setup method
    public AddBookDialog(TopMenuController controller) {
        this.topMenuController = controller;
        this.iconBarController = null;
        setUpDialog();
    }
    
    // Constructor called by IconBarController 
    //Initialise controllers, and call private setup method
    public AddBookDialog(IconBarController controller) {
        this.iconBarController = controller;
        this.topMenuController = null;
        setUpDialog();
    }
    
    //Private helper method. Used by both Constructors.
    private void setUpDialog() {
        
        //Configure NumberFormat and NumberFormatter to allow 
        //Integers, and with a range of 7 digits
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        integerFormat.setGroupingUsed(false);
        NumberFormatter numberFormatter = new NumberFormatter(integerFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(true);
        numberFormatter.setMinimum(0000001);
        numberFormatter.setMaximum(9999999);
                                
        //Initialise bookCode variable, and set up layout of dialog box
        this.bookCode = -1;
        this.setLayout(new GridLayout(0, 1));
                
        //Create new fields
        bookCodeField = new JFormattedTextField(numberFormatter);
        bookNameField =  new JTextField();
                
        //Add required components to this JPanel
        this.add(new JLabel("Book Code"));
        this.add(bookCodeField);
        this.add(new JLabel("Book Title"));
        this.add(bookNameField);
        
        int result = JOptionPane.OK_OPTION;
        
        //Will validate user input, and keep opening dialog while 
        //not valid or cancelled
        while (result == JOptionPane.OK_OPTION && (
                bookCodeField.getValue() == null
                || bookNameField.getText().equals("")
                || bookNameField.getText().length() < 3 
                || bookCodeField.getText().length() != 7)) {
            //Initiate user dialog
            result = JOptionPane.showConfirmDialog(null, this,
                    "Add Book", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            //Validate user input, execute if valid
            if (result == JOptionPane.OK_OPTION && bookCodeField.getValue() != null 
                    && !bookNameField.getText().equals("") 
                    && bookNameField.getText().length() >= 3 
                    && bookCodeField.getText().length() == 7) {
                //Execute getters for book details, set not cancelled
                this.bookCode = (Integer)bookCodeField.getValue();
                this.bookName = bookNameField.getText();
                //Execute setters depending on what class called the dialog
                if (topMenuController != null) {
                    this.topMenuController.setAddBookCode(bookCode);
                    this.topMenuController.setAddBookName(bookName);
                    this.topMenuController.setCancelled(false);
                }
                else {
                    this.iconBarController.setAddBookCode(bookCode);
                    this.iconBarController.setAddBookName(bookName);
                    this.iconBarController.setCancelled(false);
                }
            }
            
            //Execute if all boook details not entered
            else if (result == JOptionPane.OK_OPTION && (
                    bookCodeField.getValue() == null 
                    || bookNameField.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, 
                        "Please enter all Book details!");
            }
            
            //OR ELSE execute if code is not 7 digits
            else if (result == JOptionPane.OK_OPTION 
                    && bookCodeField.getText().length() < 7) {
                System.out.println(bookCodeField.getText().length());
                JOptionPane.showMessageDialog(null, 
                        "Please enter a 7 digit code!");
            }
            
            //Or Else execute if name is not 3 digits or more
            else if (result == JOptionPane.OK_OPTION 
                    && bookNameField.getText().length() < 3) {
                JOptionPane.showMessageDialog(null,
                        "Book name must be three characters or greater!");
            }
            
            //Or else cancel, and set cancelled condition to true
            else {
                if (this.topMenuController != null) {
                    this.topMenuController.setCancelled(true);
                }
                else {
                    this.iconBarController.setCancelled(true);
                }
            }
        }
    }
}
