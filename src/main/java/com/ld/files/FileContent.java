package com.ld.files;

import lombok.*;

/**
 * @author Luisdany Pernillo
 */

@Getter
@Builder
@EqualsAndHashCode
public class FileContent {

    private String fileName;
    private String path;
    private String checkSum;

    @Override
    public String toString() {
        return "FileContent{" +
                "fileName='" + fileName + '\'' +
                ", path='" + path + '\'' +
                ", checkSum='" + checkSum + '\'' +
                "}\n";
    }
}
