package com.ld.print;

import com.ld.files.FileContent;

import java.util.List;

/**
 * @author luisdany pernillo
 */

public class Printer {

    public static void printFiles(final List<FileContent> contents){
        System.out.println("File: ");
        contents.forEach(c -> System.out.println(c.getPath()));
    }
}
