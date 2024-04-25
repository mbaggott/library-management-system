package controller;

import model.facade.*;
import view.StatusBar;

public class StatusBarController {
    
    private LMSModel model;
    private StatusBar statusBar;
    
    public StatusBarController(StatusBar statusBar) {
        this.statusBar = statusBar;
        this.model = statusBar.getFrame().getModel();
    }
    
    public void initStatusBar() {
        displayCollectionCode();
        displayTotalBooks();
        displayTotalVideos();
    }
    
    public void updateStatusBar() {
        displayTotalBooks();
        displayTotalVideos();
    }
    
    public void displayCollectionCode() {
        statusBar.setLibCode(model.getCollection().getCode());
    }
    
    public void displayTotalBooks() {
        statusBar.setTotalBooks(model.countBooks());
    }
    
    public void displayTotalVideos() {
        statusBar.setTotalVideos(model.countVideos());
    }
}
