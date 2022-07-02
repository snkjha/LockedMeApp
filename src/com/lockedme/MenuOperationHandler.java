package com.lockedme;

import java.util.List;

public class MenuOperationHandler {
    Utilities utilities = new Utilities();
    FileOperationHandler fileOperationHandler = new FileOperationHandler();

    public void doMainMenuOperations(){
        boolean isRunning = true;
        do {
            utilities.showMainMenuMsg();
            String choice = utilities.getInput().next();
            switch (choice){
                case "1" -> fileOperationHandler.showAllFilesInASCOrder(Utilities.MAIN_DIR_NAME);
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
                        System.out.println("Invalid Answer!\n");
                        doMainMenuOperations();
                    }
                    isRunning = false;
                }
                default -> System.out.println("""
                        Sorry! This is not a valid answer. Please enter a valid option number
                        """);
            }
        }while (isRunning);
    }

    private void doFileOperations() {
        boolean isRunning = true;
        do {
            utilities.showFileOperationMsg();
            String choice = utilities.getInput().next();
            switch (choice) {
                case "1" -> {
                    System.out.print("\nEnter File Name to be added:\n» ");
                    String fileName = utilities.getInput().next();
                    fileOperationHandler.createAFiles(Utilities.MAIN_DIR_NAME, fileName, utilities.getInput());
                }
                case "2" -> {
                    System.out.print("\nEnter the name of the file to search\n» ");
                    String fileName = utilities.getInput().next();
                    fileOperationHandler.getListOfSearchedFile(Utilities.MAIN_DIR_NAME, fileName);

                }
                case "3" -> {
                    System.out.print("\nEnter the name of the file to delete\n» ");
                    String fileToBeDeleted = utilities.getInput().next();
                    fileOperationHandler.createEmptyDirectoryIfNotFound(Utilities.MAIN_DIR_NAME);
                    List<String> listOfFilesToDelete = fileOperationHandler.getListOfSearchedFile(Utilities.MAIN_DIR_NAME, fileToBeDeleted);
                    if (!listOfFilesToDelete.isEmpty()) {
                        System.out.print("""
                                Select index of the file you want to delete.
                                Enter 0 if you want to delete all element
                                »\040""");
                    } else {
                        doFileOperations();
                    }
                    try {
                        int index = Integer.parseInt(utilities.getInput().next());
                        if (index != 0) {
                            fileOperationHandler.deleteFile(listOfFilesToDelete.get(index - 1));
                        } else {
                            for (String path : listOfFilesToDelete) {
                                fileOperationHandler.deleteFile(path);
                            }
                        }
                    } catch (NumberFormatException nfe) {
                        System.out.println("Your have to enter on index number and this is not an number\n");
                    }
                }
                case "4" -> {
                    System.out.println("Going Back.");
                    doMainMenuOperations();
                }
                case "5" -> {
                    System.out.print("Do you really want to exit(Y/N)\n» ");
                    String ans = utilities.getInput().next();
                    if (ans.equalsIgnoreCase("y")) {
                        utilities.doNotTakeMoreInputs();
                        System.exit(0);
                    } else if (ans.equalsIgnoreCase("n")) {
                        doFileOperations();
                    } else {
                        System.out.println("Invalid Answer!\n");
                        doFileOperations();
                    }
                    isRunning = false;
                }
                default -> System.out.println("""
                        Sorry! This is not a valid answer. Please enter a valid option number
                        """);
            }
        } while (isRunning);
    }
}