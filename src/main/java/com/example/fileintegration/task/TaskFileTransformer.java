package com.example.fileintegration.task;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class TaskFileTransformer {

    public String transformFile(String fileName) throws IOException {

        File resultFile = new File("taskdata/output/result.txt");
        FileUtils.writeStringToFile(resultFile, fileName + "\n", "UTF-8", true);
        return fileName;
    }

}
