package view;

import javax.swing.*;

import controller.RemoveVideoDialogController;

@SuppressWarnings("serial")
//GUI Class
public class RemoveVideoDialog extends JDialog {

    //Declare instance variables
    ViewFrame frame;
    RemoveVideoDialogController controller;
    DefaultListModel<String> listModel;
    JList<String> list;
    JScrollPane scrollPane;
    JLabel listHeading;
    JOptionPane optionPane;
    JPanel buttonPanel;
    JButton okButton;
    JButton cancelButton;
    
    // *** CONSTRUCTOR ***
    
    public RemoveVideoDialog(String[] listValues, ViewFrame frame) {
        
        //Set name of dialog box
        super(frame, "Remove Video", true);
        //Assign frame, and create and assign controller
        this.frame = frame;
        controller = new RemoveVideoDialogController(this);
        
        //Build Dialog
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        listModel = new DefaultListModel<String>();  
        list = new JList<String>(listModel);
        scrollPane = new JScrollPane(list);
        listHeading = new JLabel("REMOVE VIDEO", JLabel.CENTER);
        buttonPanel = new JPanel();
        okButton = new JButton("OK");
        cancelButton = new JButton("CANCEL");
        
        //Add all videos to Dialog JList
        for (int x = 0; x < listValues.length; x++) {
            listModel.addElement(listValues[x]);
        }
        
        //Configure JList settings
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(listValues.length);
        
        //Add column heading and horizontal scrollbar if needed
        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setColumnHeaderView(listHeading);
        
        //Set Action Commands for OK and Cancel buttons
        okButton.setActionCommand("OK");
        cancelButton.setActionCommand("CANCEL");
        
        //Add OK and Cancel buttons to button panel
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
                
        //Add all components to this JPanel
        this.add(scrollPane);
        this.add(buttonPanel);
        this.setLocation(frame.getX() + 100, frame.getY() + 50);
        this.pack();
        
        //Add listners to OK and Cancel buttons
        okButton.addActionListener(controller);
        cancelButton.addActionListener(controller);
        
        //Make dialog visible
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
