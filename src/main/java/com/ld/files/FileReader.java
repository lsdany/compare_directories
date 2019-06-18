package com.ld.files;


import com.ld.checksum.CheckSum;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class FileReader {

    private List<FileContent> fileContentsOrigin;
    private List<FileContent> fileContentsDestination;

    private int dotCount = 1;
    private AtomicBoolean isAlive = new AtomicBoolean(true);



    public FileReader(){
        fileContentsOrigin = new ArrayList<>();
        fileContentsDestination = new ArrayList<>();
    }

    public void read(final String directoryOrigin, final String directoryDestination){
        File fileOrigin = new File(directoryOrigin);

        System.out.println("Comparing");
        Thread thread = new Thread(progressDots());
        thread.start();

        getFiles(fileOrigin, 'o');
        File fileDestination = new File(directoryDestination);
        getFiles(fileDestination, 'd');

        isAlive.set(false);

        System.out.printf("\nOrigin files read: %d \n", fileContentsOrigin.size());
        System.out.printf("Destination files read: %d \n", fileContentsDestination.size());

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
                }else{
                    fileContentsDestination.add(createFileContent(f));
                }
            }
        });
    }

    private FileContent createFileContent(final File file){
        String checkSum = CheckSum.getFileChecksum(file);
        return FileContent.builder().fileName(file.getName()).path(file.getPath()).checkSum(checkSum).build();
    }


    public static void main(String[] args) {
        FileReader fr = new FileReader();
        fr.read("/home/luisdany/folderTest/folderA/","/home/luisdany/folderTest/folderB/");
    }

    private Runnable progressDots(){
        return () -> {
            while (isAlive.get()){
                try{
//                    System.out.println(Thread.currentThread().getName());
                    if (dotCount%100 == 0)
                        System.out.println(".");
                    else
                        System.out.print(".");
                    dotCount++;
                    Thread.sleep(5);
                }catch (InterruptedException e){
                    System.out.println("error sleeping...");
                }
            }
        };

    }

}
