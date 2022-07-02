package com.lockedme;

import java.util.Scanner;

public class Utilities {
    public static final String MAIN_DIR_NAME = "My Files";


    public Scanner getInput(){
        return new Scanner(System.in);
    }

    public void doNotTakeMoreInputs(){
        getInput().close();
    }

    public void showWelcomeMsg(){
        final String WELCOME_MSG = """
            +-----------------------------------------------+
            | Welcome to LockedMe App!                      |
            +-----------------------------------------------+
            | Company Name: Lockers Pvt. Ltd.               |
            | Developer's Name: Sandeep Kumar Jha           |
            | Developer's Email: snkjha@outlook.com         |
            +-----------------------------------------------+
            """;
        System.out.print(WELCOME_MSG);
    }

    public void showMainMenuMsg(){
        final String MAIN_MENU_OPTION_MSG = """
                +-----------------------------------------------+
                | MAIN MENU                                     |
                +-----------------------------------------------+
                | 1. Display All Files                          |
                | 2. File Operations                            |
                | 3. Exit                                       |
                +-----------------------------------------------+
                Please enter option number from above and
                press enter (Only numeric value are allowed)
                »\040""";
        System.out.print(MAIN_MENU_OPTION_MSG);
    }

    public void showFileOperationMsg(){
       final String ASK_FILE_MENU_OPTION = """
                +-----------------------------------------------+
                | FILE OPERATION OPTIONS                        |
                +-----------------------------------------------+
                | 1. Add File                                   |
                | 2. Search File With File Name                 |
                | 3. Delete File                                |
                | 4. Go Back to Main Menu                       |
                | 5. Exit                                       |
                +-----------------------------------------------+
                Please enter option number from above and
                press enter (Only numeric value are allowed)
                »\040""";
        System.out.print(ASK_FILE_MENU_OPTION);
    }
}
