package view.visitor;

import model.*;

//Interface for Visitor Pattern
public interface Visitable {
    public String accept(Holding holding);
}
