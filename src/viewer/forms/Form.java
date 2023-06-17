/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer.forms;

import viewer.InputHandler;
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
    /**
     * Constructs a new Form object with the specified InputHandler.
     * 
     * @param ih The InputHandler object to use for user input.
     */
    public Form(InputHandler ih) {
        this.ih = ih;
    }
    /**
     * Takes user input for the form.
     * Subclasses should implement this method to collect user input for all attributes.
     */
    public abstract void takeUserInput();
}
