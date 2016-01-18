package lms.view.visitor;

import lms.model.*;

//Interface for Visitor Pattern
public interface Visitor {
    
    public String visit(Holding holding);

}
