package com.lockedme;

public class MenuOperationHandler {
    Utilities utilities = new Utilities();
    FileOperationHandler fileOperationHandler = new FileOperationHandler();

    public void doMainMenuOperations(){
        boolean isRunning = true;
        do {
            utilities.showMainMenuMsg();
            String choice = utilities.getInput().next();
            switch (choice){
                case "1" -> fileOperationHandler.showAllFiles();
                case "2" -> doFileOperations();
                case "3" -> {
                    System.out.print("Do you really want to exit(Y/N)\n» ");
                    String ans = utilities.getInput().next();
                    if(ans.equalsIgnoreCase("y")){
                        utilities.doNotTakeMoreInputs();
                        System.exit(0);
                    }else if (ans.equalsIgnoreCase("n")){
                        doMainMenuOperations();
                    }else{
                        System.out.println("Invalid Answer!");
                    }
                    isRunning = false;
                }
                default -> System.err.println("""
                        Sorry! This is not a valid answer. Please enter a valid option number
                        """);
            }
        }while (isRunning);
    }

    private void doFileOperations(){
        boolean isRunning = true;
        do {
            utilities.showFileOperationMsg();
            String choice = utilities.getInput().next();
            switch (choice){
                case "1" -> fileOperationHandler.createAFiles();
                case "2" -> fileOperationHandler.searchForAFiles();
                case "3" -> fileOperationHandler.removeAFiles();
                case "4" -> {
                    System.out.println("Going Back.");
                    doMainMenuOperations();
                }
                case "5" -> {
                    System.out.print("Do you really want to exit(Y/N)\n» ");
                    String ans = utilities.getInput().next();
                    if(ans.equalsIgnoreCase("y")){
                        utilities.doNotTakeMoreInputs();
                        System.exit(0);
                    }else if (ans.equalsIgnoreCase("n")){
                        doFileOperations();
                    }else{
                        System.out.println("Invalid Answer!");
                    }
                    isRunning = false;
                }
                default -> System.err.println("""
                        Sorry! This is not a valid answer. Please enter a valid option number
                        """);
            }
        }while (isRunning);
    }
}
