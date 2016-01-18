package lms.view;

import java.awt.*;
import javax.swing.*;
import lms.model.facade.*;
import lms.view.util.*;

@SuppressWarnings("serial")
//GUI Class
public class ViewFrame extends JFrame {
    
    //Declare instance variables
    private LMSModel model;
    private Tester tester;
    private BorderPane borderPane;
    private ContentPane contentPane;
    private TopMenu topMenu;
    private StatusBar statusBar;
        
    // *** CONSTRUCTORS ***
    public ViewFrame(LMSModel model, Tester tester) {
        
        //Give the frame a name
        super("Library Management System GUI");
        
        //Assign variables
        this.model = model;
        this.tester = tester;
        //Set initial Frame size
        this.setSize(800, 600);
        //Set minumum frame size
        this.setMinimumSize(new Dimension(350,250));
        //Set the frame to use the window listener to exit
        this.addWindowListener(new ExitListener(this));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        
        //Build Components
        borderPane = new BorderPane();
        contentPane = new ContentPane(this);
        topMenu = new TopMenu(this);
        statusBar = new StatusBar(this);
        borderPane.add(topMenu, BorderLayout.NORTH);
        borderPane.add(statusBar, BorderLayout.SOUTH);
        borderPane.add(contentPane, BorderLayout.CENTER);
        //Add components to this Frame
        this.add(borderPane);
        
   
    }
    
    // *** GETTERS ***
    public LMSModel getModel() {
        return this.model;
    }
    
    public Tester getTester() {
        return this.tester;
    }
    
    public TopMenu getTopMenu() {
        return this.topMenu;
    }
    
    public StatusBar getStatusBar() {
        return this.statusBar;
    }
    
    public ContentPane getContentPanel() {
        return this.contentPane;
    }
    
    //Counts the total number of holdings in the collection
    public int countHoldings() {
        return model.countBooks() + model.countVideos();
    }

}
