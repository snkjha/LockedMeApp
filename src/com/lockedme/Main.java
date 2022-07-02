package com.lockedme;

public class Main {

    public static void main(String[] args) {
	    Utilities utilities = new Utilities();
        MenuOperationHandler menuHandler = new MenuOperationHandler();
        utilities.showWelcomeMsg();
        menuHandler.doMainMenuOperations();

    }
}
