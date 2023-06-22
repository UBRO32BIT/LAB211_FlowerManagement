/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms;

import view.InputHandler;
/**
 * The base class for all forms.
 * Provides a common structure and functionality for form classes.
 * Each form represents a user input form for specific operations.
 * Subclasses should implement the `takeUserInput` method to collect user input on all attributes.
 * 
 * @author ubro3
 */
public abstract class Form {
    protected final InputHandler ih;
    protected final String header;
    /**
     * Constructor with the specified InputHandler.
     * 
     * @param ih The InputHandler object to use for user input.
     * @param header The header of the form
     */
    public Form(InputHandler ih, String header) {
        this.ih = ih;
        this.header = header;
    }
    /**
    * Prints the header for the form.
    * The header is a formatted string displayed at the beginning of the form.
    * It helps visually separate different forms or sections of the program.
    */
    protected void printHeader() {
        System.out.println("\n" + "---------- " + header + " ----------");
    }
    
    /**
     * Takes user input for the form.
     * Subclasses should implement this method to collect user input for all attributes.
     */
    public abstract void takeUserInput();
}
