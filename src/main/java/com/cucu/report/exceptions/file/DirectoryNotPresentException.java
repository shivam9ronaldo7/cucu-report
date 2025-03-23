package com.cucu.report.exceptions.file;

import com.cucu.report.exceptions.CucuException;

public class DirectoryNotPresentException extends CucuException {

    public DirectoryNotPresentException(final String directory) {
        super(String.format("Directory: %s does not exist", directory));
    }

}
