package com.ld.files;

import com.ld.print.Printer;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author luisdany pernillo
 */

public class FileComparator {

    private List<FileContent> fileContentsOrigin;
    private List<FileContent> fileContentsDestination;

    public void compareDirectories(final List<FileContent> fileContentsOrigin, final List<FileContent> fileContentsDestination){

        this.fileContentsOrigin = fileContentsOrigin;
        this.fileContentsDestination = fileContentsDestination;

//        System.out.println("Comparing files");

        List<String> checkSumOrigin = fileContentsOrigin
                .stream()
                .map(FileContent::getCheckSum)
                .collect(Collectors.toList());

        List<String> checkSumDestination = fileContentsDestination
                .stream()
                .map(FileContent::getCheckSum)
                .collect(Collectors.toList());


        List<String> filesDisjunction = new ArrayList<>(CollectionUtils.disjunction(checkSumOrigin, checkSumDestination));

//        System.out.println("Different files: "+ filesDisjunction);
        getFilesByCheckSum(filesDisjunction);
    }

    private void getFilesByCheckSum(final List<String> checkSums){

        Stream<FileContent> differentFiles1 = fileContentsOrigin.stream()
                .filter(f -> checkSums.contains(f.getCheckSum()));
        Stream<FileContent> differentFiles2 = fileContentsDestination.stream()
                .filter(f -> checkSums.contains(f.getCheckSum()));

        List<FileContent> differentFiles = Stream.concat(differentFiles1, differentFiles2)
                .distinct()
                .collect(Collectors.toList());

        Printer.printFiles(differentFiles);

    }
}
