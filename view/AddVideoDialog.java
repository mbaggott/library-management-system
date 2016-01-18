package lms.view;

import javax.swing.*;

import java.awt.*;
import java.text.NumberFormat;
import lms.controller.TopMenuController;
import lms.controller.IconBarController;
import javax.swing.text.NumberFormatter;

@SuppressWarnings("serial")
//GUI Class
public class AddVideoDialog extends JPanel {

    //Declare instance variables
    private TopMenuController topMenuController;
    private IconBarController iconBarController;
    private JFormattedTextField videoCodeField;
    private JTextField videoNameField;
    private String videoName;
    private int videoCode, videoLoanFee;
    
    // *** CONSTRUCTORS ***
    
    // Constructor called by TopMenuController 
    //Initialise controllers, and call private setup method
    public AddVideoDialog(TopMenuController topMenuController) {
        this.topMenuController = topMenuController;
        this.iconBarController = null;
        setupDialog();
    }
    
    // Constructor called by IconBarController 
    //Initialise controllers, and call private setup method
    public AddVideoDialog(IconBarController iconBarController) {
        this.iconBarController = iconBarController;
        this.topMenuController = null;
        setupDialog();
    }
    
    //Private helper method. Used by both Constructors.
    private void setupDialog() {
        
        //Initialise int video details variables
        this.videoCode = -1;
        this.videoLoanFee = -1;
                
        //Configure NumberFormat and NumberFormatter to allow Integers,
        //and with a range of 7 digits
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        integerFormat.setGroupingUsed(false);
        NumberFormatter numberFormatter = new NumberFormatter(integerFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(true);
        numberFormatter.setMinimum(0000001);
        numberFormatter.setMaximum(9999999);
        
        //Set up layout of dialog box
        this.setLayout(new GridLayout(0, 1));
        //Create new fields
        videoCodeField = new JFormattedTextField(numberFormatter);
        videoNameField =  new JTextField();
                
        //Add required components to this JPanel
        this.add(new JLabel("Video Code"));
        this.add(videoCodeField);
        this.add(new JLabel("Video Title"));
        this.add(videoNameField);
        String[] loanFeeValues = {"Choose", "4", "6"};
        JComboBox<String> chooseFee = new JComboBox<String>(loanFeeValues);
        chooseFee.setSelectedIndex(0);
        this.add(new JLabel("Loan Fee ($)"));
        this.add(chooseFee);
        int result = JOptionPane.OK_OPTION;
        
        //Will validate user input, and keep opening dialog 
        //while not valid or cancelled
        while (result == JOptionPane.OK_OPTION && (videoCodeField.getValue()
                == null || videoNameField.getText().equals("") 
                || chooseFee.getSelectedItem().equals("Choose")  
                || videoNameField.getText().length() < 3 
                || videoCodeField.getText().length() != 7)) {
            //Initiate user dialog
            result = JOptionPane.showConfirmDialog(null, this, "Add Video",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            //Validate user input, execute if valid
            if (result == JOptionPane.OK_OPTION && videoCodeField.getValue() != null
                    && !videoNameField.getText().equals("") 
                    && videoNameField.getText().length() >= 3 
                    && videoCodeField.getText().length() == 7 
                    && !chooseFee.getSelectedItem().equals("Choose")) {
                //Execute getters for book details, set not cancelled
                this.videoCode = (Integer)videoCodeField.getValue();
                this.videoName = videoNameField.getText();
                this.videoLoanFee = Integer.parseInt(
                        chooseFee.getSelectedItem().toString());
                //Execute setters depending on what class called the dialog
                if (this.topMenuController != null) {
                    this.topMenuController.setAddVideoCode(videoCode);
                    this.topMenuController.setAddVideoName(videoName);
                    this.topMenuController.setAddVideoLoanFee(videoLoanFee);
                    this.topMenuController.setCancelled(false);
                }
                else {
                    this.iconBarController.setAddVideoCode(videoCode);
                    this.iconBarController.setAddVideoName(videoName);
                    this.iconBarController.setAddVideoLoanFee(videoLoanFee);
                    this.iconBarController.setCancelled(false);
                }
            }
            
            //Execute if all video details not entered
            else if (result == JOptionPane.OK_OPTION && (
                    videoCodeField.getValue() == null 
                    || videoNameField.getText().equals("") 
                    || chooseFee.getSelectedItem().equals("Choose"))) {
                JOptionPane.showMessageDialog(null,
                        "Please enter all Video details!");
            }
            
            //OR ELSE execute if code is not 7 digits
            else if (result == JOptionPane.OK_OPTION 
                    && videoCodeField.getText().length() < 7) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a 7 digit code!");
            }   
            
            //Or Else execute if name is not 3 digits or more
            else if (result == JOptionPane.OK_OPTION 
                    && videoNameField.getText().length() < 3) {
                JOptionPane.showMessageDialog(null, 
                        "Video name must be three characters or greater!");
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
