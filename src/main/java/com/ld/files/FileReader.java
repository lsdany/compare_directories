package com.ld.files;


import com.ld.checksum.CheckSum;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;


public class FileReader {

    private List<FileContent> fileContentsOrigin;
    private List<FileContent> fileContentsDestination;

    private int counterOrigin;
    private int counterDestination;

    public FileReader(){
        fileContentsOrigin = new ArrayList<>();
        fileContentsDestination = new ArrayList<>();
    }

    public void read(final String directoryOrigin, final String directoryDestination){
        File fileOrigin = new File(directoryOrigin);
        getFiles(fileOrigin, 'o');
//        System.out.printf("Origin files read: %d \n",counterOrigin);
        File fileDestination = new File(directoryDestination);
        getFiles(fileDestination, 'd');
//        System.out.printf("Destination files read: %d \n",counterDestination);

        FileComparator fileComparator = new FileComparator();
        fileComparator.compareDirectories(fileContentsOrigin, fileContentsDestination);


    }

    private void getFiles(final File file, final char operation){
        File[] files = file.listFiles();
        Arrays.asList(files).parallelStream().forEach(f -> {
            if(f.isDirectory()){
                getFiles(f, operation);
            }else{
                if(operation == 'o'){
                    fileContentsOrigin.add(createFileContent(f));
                    counterOrigin++;
                }else{
                    fileContentsDestination.add(createFileContent(f));
                    counterDestination++;
                }
            }
        });
    }

    private FileContent createFileContent(final File file){
        String checkSum = CheckSum.getFileChecksum(file);
        FileContent fileContent =FileContent.builder().fileName(file.getName()).path(file.getPath()).checkSum(checkSum).build();
//        System.out.println(fileContent.toString());
        return fileContent;
    }


    public static void main(String[] args) {
        FileReader fr = new FileReader();
        fr.read("/home/luisdany/folderTest/folderA/","/home/luisdany/folderTest/folderB/");
    }
}
