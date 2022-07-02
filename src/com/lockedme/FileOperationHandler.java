package com.lockedme;


public class FileOperationHandler {
    public void createEmptyDirectoryIfNotFound(){
        String DIR = Utilities.MAIN_DIR_NAME;
        System.out.println(DIR);
    }
    public void showAllFiles(){
        createEmptyDirectoryIfNotFound();
        System.out.println("All files");
    }
    public void createAFiles(){
        createEmptyDirectoryIfNotFound();
        System.out.println("file added");
    }
    public void searchForAFiles(){
        createEmptyDirectoryIfNotFound();
        System.out.println("file found");
    }
    public void removeAFiles(){
        createEmptyDirectoryIfNotFound();
        System.out.println("file deleted");
    }

}
