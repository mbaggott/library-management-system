package lms.view;

import java.awt.*;
import javax.swing.*;
import lms.controller.SortController;

@SuppressWarnings("serial")
//GUI Class
public class SortControls extends JPanel {
    
    //Declare instance variables 
    private TopMenu topMenu;
    private SortController controller;
    private GridLayout grid;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel gridPanel;
    private JLabel nameLabel;
    private ButtonGroup group;
    private JRadioButton none;
    private JRadioButton code;
    private JRadioButton type;
    
    /// *** CONSTRUCTOR ***
    
    public SortControls(TopMenu topMenu) {
        
        //Assign variable, set and assign controller and set layout
        this.topMenu = topMenu;
        controller = new SortController(this);
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        //Build sort Controls
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        gridPanel = new JPanel();
        grid = new GridLayout(0,1);
        grid.setHgap(0);
        grid.setVgap(0);
        gridPanel.setLayout(grid);
        
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        nameLabel = new JLabel("SORT ORDER");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        topPanel.add(nameLabel);
        
        group = new ButtonGroup();
        none = new JRadioButton("NONE");
        code = new JRadioButton("CODE");
        type = new JRadioButton("TYPE");
        
        //Set action commands for the three controls
        none.setActionCommand(none.getText());
        code.setActionCommand(code.getText());
        type.setActionCommand(type.getText());
        
        //Add action listeners to the three controls
        none.addActionListener(controller);
        code.addActionListener(controller);
        type.addActionListener(controller);
        
        //Configure controls spacing       
        none.setSelected(true);
        none.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        code.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        type.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        //Add the sort controls to the panels, and group them
        group.add(none);
        group.add(code);
        group.add(type);
        bottomPanel.add(none);
        bottomPanel.add(code);
        bottomPanel.add(type);
        
        //Add the panels to the main panel, then add to this JPanel
        gridPanel.add(topPanel);
        gridPanel.add(bottomPanel);
        this.add(gridPanel);
        
    }

    // *** GETTERS ***
    
    public TopMenu getTopMenu() {
        return this.topMenu;
    }
    
    public SortController getSortController() {
        return this.controller;
    }
}
