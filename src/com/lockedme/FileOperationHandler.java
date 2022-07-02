package com.lockedme;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class FileOperationHandler {
    public void createEmptyDirectoryIfNotFound(String dir){
        File newDir = new File(dir);
        if(!newDir.exists()){
            if (newDir.mkdir()){
                System.out.printf("A new folder with name %s has been created",dir);
            }
        }
    }
    public void showAllFilesInASCOrder(String mainFolderName){
        createEmptyDirectoryIfNotFound(mainFolderName);
        System.out.println("""

                Displaying all file with directory structure(in ascending order)
                """);
        System.out.println("📁 "+mainFolderName);
        String mainFolderPath = ".\\"+mainFolderName;
        getListOfFilesInASCOrder(mainFolderPath,1, new ArrayList<>());
        System.out.println();
    }

    private void getListOfFilesInASCOrder(String mainFolderPath, int indentation, List<String> fileAndFolderList) {
        File filesInDir = new File(mainFolderPath);
        File[] files = filesInDir.listFiles();
        List<File> listOfFiles = null;
        if (files!=null) listOfFiles = new ArrayList<>(Arrays.asList(files));
        if (listOfFiles!=null){
            Collections.sort(listOfFiles);
        }
        if(files!=null && files.length>0){
            for (File file:listOfFiles) {
                System.out.print(" ".repeat(indentation*2));
                if (file.isDirectory()){
                    System.out.println("📂 "+file.getName());
                    fileAndFolderList.add(file.getName());
                    getListOfFilesInASCOrder(file.getAbsolutePath(),indentation+1,fileAndFolderList);
                }else{
                    System.out.println("📄 "+file.getName());
                    fileAndFolderList.add(file.getName());
                }
            }
        }else{
            System.out.print(" ".repeat(indentation*2));
            System.out.print("📁 Empty Directory");
        }
    }

    public void createAFiles(String mainFolderName, String fileName, Scanner scan){
        createEmptyDirectoryIfNotFound(mainFolderName);
        String filePath = ".\\%s\\%s".formatted(mainFolderName,fileName);
        File newFile = new File(filePath);
        try {
            boolean isFileCreated = newFile.createNewFile();
            if(isFileCreated)
            {
                System.out.println("A new file with name "+fileName
                        +" has been created.\nLocation "+newFile.getAbsolutePath()+".");
                writeContentToFile(scan, Paths.get(newFile.getAbsolutePath()));
            }else {
                System.out.println(fileName+" already exist. File Path: "
                        +newFile.getAbsolutePath()+"\n");
            }
        } catch (IOException e) {
            System.out.println("Sorry, but you have entered an invalid file name or path\n");
        }

    }

    private void writeContentToFile(Scanner scan, Path path) {
        System.out.print("\nWould you like to add some content to you file?(Y/N)\n» ");
        String option = scan.next();
        scan.nextLine();
        switch (option.toLowerCase()){
            case "y" ->{
                System.out.print("""

                        Enter you content to be added and press enter key
                        »\040""");
                String content = scan.nextLine();
                try {
                    Files.write(path,content.getBytes());
                } catch (IOException e) {
                    System.out.println("Something went wrong. Please try again");
                }
                System.out.println("\nContent has been written to file\n");
            }
            case "n" -> System.out.println();
            default -> {
                System.out.println("Sorry! This is not a valid answer. " +
                        "Please enter valid answer");
                writeContentToFile(scan,path);
            }
        }
    }

    public List<String> getListOfSearchedFile(String mainFolderName, String searchKey){
        String path = ".\\"+mainFolderName;
        List<String> listOfFiles = new ArrayList<>();
        searchFile(path, searchKey, listOfFiles);
        if (listOfFiles.isEmpty()) {
            System.err.println("Oops! Couldn't find any file with given file name"
                    + searchKey + "\n");
        } else {
            System.out.println("\nFile(s) found at these location(s) for the search key \""+searchKey+"\":\n");
            List<String> files = IntStream.range(0, listOfFiles.size())
                    .mapToObj(index -> (index + 1) + ": " +
                            listOfFiles.get(index)).toList();
            files.forEach(System.out::println);
        }
        System.out.println();
        return listOfFiles;
    }

    private void searchFile(String path, String searchKey, List<String> listOfFile){
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.getName().toLowerCase().contains(searchKey.toLowerCase())) {
                    listOfFile.add(file.getAbsolutePath());
                }
                // Need to search in directories separately to ensure all files of
                // required fileName are searched
                if (file.isDirectory()) {
                    searchFile(file.getAbsolutePath(), searchKey, listOfFile);
                }
            }
        }
    }

    public void deleteFile(String path){
        File currentFile = new File(path);
        File[] files = currentFile.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFile(file.getAbsolutePath());
                }
                if (file.delete()) {
                    System.out.println(file.getName() + " has been deleted successfully");
                } else {
                    System.out.println("Oops! Failed to delete " + file.getName());
                }
            }
        }
        if (currentFile.delete()) {
            System.out.println(currentFile.getName() + " has been deleted successfully");
        } else {
            System.out.println("Oops! Failed to delete " + currentFile.getName());
        }
    }
}
