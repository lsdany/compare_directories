package com.ld.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Luisdany Pernillo
 */

public class FileWriter {

    private final static String FILE_NAME = "different_files.txt";

    public static void write(List<FileContent> fileContents){

        try {
            Files.write(Paths.get(FILE_NAME), fileContents.stream()
                    .map(FileContent::getPath)
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            System.out.println("Problem writing the file..." + e.getLocalizedMessage());
        }

    }


}
