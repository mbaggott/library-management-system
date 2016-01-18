package lms.view.visitor;

import lms.model.*;

//Interface for Visitor Pattern
public interface Visitable {
    public String accept(Holding holding);
}
