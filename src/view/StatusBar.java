package view;

import java.awt.*;
import javax.swing.*;
import controller.StatusBarController;

@SuppressWarnings("serial")
//GUI Class
public class StatusBar extends JPanel {
    
    //Declare instance variables
    private JLabel libCode;
    private JLabel totalBooks;
    private JLabel totalVideos;
    private StatusBarController controller;
    private ViewFrame frame;
    
    // *** CONSTRUCTOR ***
    
    public StatusBar(ViewFrame frame) {
        //Assign frame
        this.frame = frame;
        //Build StatusBar
        libCode = new JLabel();
        totalBooks = new JLabel();
        totalVideos = new JLabel();
        controller = new StatusBarController(this);
        
        //Set the layout
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        //Add components to this JPanel
        this.add(libCode);
        this.add(totalBooks);
        this.add(totalVideos);
    }
    
    // *** GETTERS ***
    public ViewFrame getFrame() {
        return this.frame;
    }
    
    public StatusBarController getStatusBarController() {
        return this.controller;
    }
    
    public void setLibCode(String libCode) {
        this.libCode.setText("Collection Code: " + libCode + " | ");
    }
    
    // *** SETTERS ***
    
    public void setTotalBooks(int totalBooks) {
        this.totalBooks.setText("Total Books: " + totalBooks + " | ");
    }
    
    public void setTotalVideos(int totalVideos) {
        this.totalVideos.setText("Total Videos: " + totalVideos);
    }
    
}
