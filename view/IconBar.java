package lms.view;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import lms.controller.IconBarController;

@SuppressWarnings("serial")
//GUI CLass
public class IconBar extends JPanel {
    
    //Declare instance variables
    private IconBarController controller;
    private ViewFrame frame;
    private JButton initCollection;
    private boolean initCollectionSwitch = false;
    private JButton addBook;
    private JButton removeBook;
    private JButton addVideo;
    private JButton removeVideo;
    
    // *** CONSTRUCTOR ***
    // Creates Icons and adds images to them
    public IconBar(ViewFrame frame) {
        this.frame = frame;
        this.controller = new IconBarController(this);
        initCollection = new JButton();
        addBook = new JButton();
        removeBook = new JButton();
        addVideo = new JButton();
        removeVideo = new JButton();
        this.setLayout(new WrapLayout(WrapLayout.LEFT));
        InputStream input1 = getClass().getClassLoader()
                .getResourceAsStream("collection.png");
        InputStream input2 = getClass().getClassLoader()
                .getResourceAsStream("book_add.png");
        InputStream input3 = getClass().getClassLoader()
                .getResourceAsStream("book_delete.png");
        InputStream input4 = getClass().getClassLoader()
                .getResourceAsStream("film_add.png");
        InputStream input5 = getClass().getClassLoader()
                .getResourceAsStream("film_delete.png");
        
        //Try catch, incase image files cannot be read
        try {
            Image initCollectionImg = ImageIO.read(input1);
            Image bookAddImg = ImageIO.read(input2);
            Image bookDeleteImg = ImageIO.read(input3);
            Image videoAddImg = ImageIO.read(input4);
            Image videoDeleteImg = ImageIO.read(input5);
            initCollection.setIcon(new ImageIcon(initCollectionImg));
            addBook.setIcon(new ImageIcon(bookAddImg));
            removeBook.setIcon(new ImageIcon(bookDeleteImg));
            addVideo.setIcon(new ImageIcon(videoAddImg));
            removeVideo.setIcon(new ImageIcon(videoDeleteImg));
        }
        catch (Exception e){
            System.out.println("Error reading image file for icon!");
        }
        
        // ** Set up accessibility values, and add cotnrollers 
        //to listen for actions
        
        initCollection.getAccessibleContext()
                .setAccessibleDescription("Init/Reset Library");
        initCollection.setActionCommand("Init/Reset Library");
        initCollection.addActionListener(controller);
        
        addBook.getAccessibleContext().setAccessibleDescription("Add Book");
        addBook.setActionCommand("Add Book");
        addBook.addActionListener(controller);
        
        removeBook.getAccessibleContext().setAccessibleDescription("Remove Book");
        removeBook.setActionCommand("Remove Book");
        removeBook.addActionListener(controller);
        
        addVideo.getAccessibleContext().setAccessibleDescription("Add Video");
        addVideo.setActionCommand("Add Video");
        addVideo.addActionListener(controller);
        
        removeVideo.getAccessibleContext()
                .setAccessibleDescription("Remove Video");
        removeVideo.setActionCommand("Remove Video");
        removeVideo.addActionListener(controller);
        
        //Add components to this JPanel
        this.add(initCollection);
        this.add(addBook);
        this.add(removeBook);
        this.add(addVideo);
        this.add(removeVideo);
        
    }
    
    // *** GETTERS ***
    public ViewFrame getFrame() {
        return this.frame;
    }
    
    public IconBarController getController() {
        return this.controller;
    }
    
    public boolean getInitCollectionSwitch() {
        return this.initCollectionSwitch;
    }
    
    //Publicly used method to set the already initialised variable to true or false
    public boolean setInitCollectionSwitch() {
        if (!this.initCollectionSwitch) {
            this.initCollectionSwitch = true;
        }
        else {
            this.initCollectionSwitch = false;
        }
        return this.initCollectionSwitch;
    }
    
}
