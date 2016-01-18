package lms.view.visitor;

import lms.model.*;

// Part of Visitor Pattern
public class HoldingVisitor implements Visitor {

    @Override
    //Returns a string depending on type of instance visiting
    public String visit(Holding holding) {
        if(holding instanceof Book) {
            return "Book";
        }
        else return "Video";
        
    }

}
