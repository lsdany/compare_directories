package com.ld.files;

import lombok.*;

/**
 * @author Luisdany Pernillo
 */

@Getter
@Builder
@ToString
public class FileContent {

    private String fileName;
    private String path;
    private String checkSum;

}
