package view;

import java.awt.*;

import javax.swing.*;
import controller.GridPanelController;

@SuppressWarnings("serial")
//GUI Class
public class GridPanel extends JPanel {
    
    //Declare instance variables
    private ViewFrame frame;
    private GridPanelController controller;
    private JPanel holdingCode;
    private JLabel holdingCodeLabel;
    private JLabel holdingCodeValue;
    private JPanel title;
    private JLabel titleLabel;
    private JLabel titleValue;
    private JPanel standardLoanFee;
    private JLabel standardLoanFeeLabel;
    private JLabel standardLoanFeeValue;
    private JPanel loanPeriod;
    private JLabel loanPeriodLabel;
    private JLabel loanPeriodValue;
    private String holdingType;
    
    // *** CONSTRUCTOR ***
    //Constructs GridPanel with values obtained from user input
    public GridPanel(ViewFrame frame, Integer holdingCode, String title, 
            Integer standardLoanFee, Integer loanPeriod, 
            String holdingType) {
        
        //Initialise frame, and holdingType (Obtained from visitor pattern when GridPanel created
        this.frame = frame;
        this.holdingType = holdingType;
        
        //Build GridPanel with supplied parameters 
        this.holdingCode = new JPanel();
        this.title = new JPanel();
        this.standardLoanFee = new JPanel();
        this.loanPeriod = new JPanel();
        this.holdingCodeLabel = new JLabel("Holding Code: ");
        this.holdingCodeValue = new JLabel(holdingCode.toString());
        this.titleLabel = new JLabel("Title: ");
        this.titleValue = new JLabel(title.toString());
        this.standardLoanFeeLabel = new JLabel("Standard Loan Fee: ");
        this.standardLoanFeeValue = new JLabel("$" + standardLoanFee.toString());
        this.loanPeriodLabel = new JLabel("Loan Period: ");
        this.loanPeriodValue = new JLabel(loanPeriod.toString() + " days");
        
        //Set Font of all labels
        Font plain = new Font(this.holdingCode.getFont().getName(),
                Font.PLAIN, this.holdingCode.getFont().getSize());
        holdingCodeValue.setFont(plain);
        titleValue.setFont(plain);
        standardLoanFeeValue.setFont(plain);
        loanPeriodValue.setFont(plain);
        
        //Add built components to this JPanel
        this.holdingCode.add(holdingCodeLabel);
        this.holdingCode.add(holdingCodeValue);
        this.title.add(titleLabel);
        this.title.add(titleValue);
        this.standardLoanFee.add(standardLoanFeeLabel);
        this.standardLoanFee.add(standardLoanFeeValue);
        this.loanPeriod.add(loanPeriodLabel);
        this.loanPeriod.add(loanPeriodValue);
        this.add(this.holdingCode);
        this.add(this.title);
        this.add(this.standardLoanFee);
        this.add(this.loanPeriod);
        
        //Reduce size of all borders
        this.holdingCode.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.standardLoanFee.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.loanPeriod.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        //Set layout of GridPanel object and it's labels
        this.setLayout(new BoxLayout(this, 
                BoxLayout.Y_AXIS));
        this.holdingCode.setLayout(new 
                FlowLayout(FlowLayout.LEFT));
        this.title.setLayout(new 
                FlowLayout(FlowLayout.LEFT));
        this.standardLoanFee.setLayout(new 
                FlowLayout(FlowLayout.LEFT));
        this.loanPeriod.setLayout(new 
                FlowLayout(FlowLayout.LEFT));
        
        //Create controller, and add listener for if panel ic clicked with mouse
        this.controller = new GridPanelController(this, 
                this.holdingCodeValue.getText());
        this.addMouseListener(controller);
    }
    
    // *** GETTERS ***
    public String getHoldingType() {
        return this.holdingType;
    }
    
    public ViewFrame getFrame() {
        return this.frame;
    }
}
