package com.cucu.report.exceptions.file;

import com.cucu.report.exceptions.CucuException;

public class FileNotPresentException extends CucuException {

    public FileNotPresentException(final String fileExtension) {
        super(String.format("File with extension %s does not exist", fileExtension));
    }

}
