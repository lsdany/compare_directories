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

    public FileReader(){
        fileContentsOrigin = new ArrayList<>();
    }

    public void read(final String directory){
        Path path = Paths.get(directory);
        System.out.println("directory = [" + path + "]");
        System.out.println(path.getFileName().getNameCount());
        path.getFileName().iterator().forEachRemaining(System.out::println);

        File file = new File(directory);
        File[] files = getFiles(file);
//        System.out.println(files.length);
//        List<File> fileList = Arrays.asList(files);
//        fileList.forEach(f -> {
//            if(f.isDirectory()){
//                System.out.println("--------Directory--------");
//                File[] directoriesFiles = f.listFiles();
//            }
//            System.out.println(f.getName());
//        });
    }

    private File[] getFiles(final File file){
        File[] files = file.listFiles();
        Arrays.asList(files).forEach(f -> {
            if(f.isDirectory()){
                getFiles(f);
            }
            fileContentsOrigin.add(createFileContent(f));
        });
        return null;
    }

    private FileContent createFileContent(final File file){
        String checkSum = CheckSum.getFileChecksum(file);
        FileContent fileContent =FileContent.builder().fileName(file.getName()).path(file.getPath()).checkSum(checkSum).build();
        System.out.println(fileContent.toString());
        return fileContent;
    }


    public static void main(String[] args) {
        FileReader fr = new FileReader();
        fr.read("C:\\Users\\blautechDM\\Downloads\\");
    }
}
