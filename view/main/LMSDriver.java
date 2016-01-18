package lms.view.main;

import lms.view.ViewFrame;
import lms.view.util.Tester;
import lms.model.facade.*;

//Main executable class
public class LMSDriver {
    
    //Declare instance variables
    private static ViewFrame frame;
    private static LMSModel model;
    private static Tester tester;
    
    public static void main(String[] args) {

        //Create model
        model = new LMSFacade();
        //Create tester
        tester = new Tester();
        
        //Create ViewFrame with model and tester
        frame = new ViewFrame(model, tester);
        //Show Frame
        frame.setVisible(true);
        
        
    }
}
