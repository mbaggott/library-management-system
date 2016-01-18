package lms.view;

import javax.swing.*;

import lms.controller.RemoveBookDialogController;

@SuppressWarnings("serial")
//GUI Class
public class RemoveBookDialog extends JDialog {

    //Declare instance variables
    ViewFrame frame;
    RemoveBookDialogController controller;
    DefaultListModel<String> listModel;
    JList<String> list;
    JScrollPane scrollPane;
    JLabel listHeading;
    JOptionPane optionPane;
    JPanel buttonPanel;
    JButton okButton;
    JButton cancelButton;
    
    // *** CONSTRUCTOR ***
    public RemoveBookDialog(String[] listValues, ViewFrame frame) {
        
        //Provide dialog name String
        super(frame, "Remove Book", true);
        //Set frame variable, create and set controller
        this.frame = frame;
        controller = new RemoveBookDialogController(this);
        
        //Build dialog
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        listModel = new DefaultListModel<String>();  
        list = new JList<String>(listModel);
        scrollPane = new JScrollPane(list);
        listHeading = new JLabel("REMOVE BOOK", JLabel.CENTER);
        buttonPanel = new JPanel();
        okButton = new JButton("OK");
        cancelButton = new JButton("CANCEL");
        
        //Add a list of Books to the dialog JList
        for (int x = 0; x < listValues.length; x++) {
            listModel.addElement(listValues[x]);
        }
        
        //Set up JList 
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(listValues.length);
        
        //Set heading and assign scrollbar for horizontal scrolling if needed
        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setColumnHeaderView(listHeading);
        
        //Set Action Commands for OK and Cancel button
        okButton.setActionCommand("OK");
        cancelButton.setActionCommand("CANCEL");
        
        //Add buttons to button JPanel
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
                
        //Add components to this JPanel
        this.add(scrollPane);
        this.add(buttonPanel);
        this.setLocation(frame.getX() + 100, frame.getY() + 50);
        this.pack();
        
        //Add listeners to OK and CANCEL buttons
        okButton.addActionListener(controller);
        cancelButton.addActionListener(controller);
        
        //Show dialog
        this.setVisible(true);
    }
    
    // *** GETTERS ***
    public ViewFrame getFrame() {
        return this.frame;
    }
    
    public String getValue() {
        return list.getSelectedValue();
    }

}
