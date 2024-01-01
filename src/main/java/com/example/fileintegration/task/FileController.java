package com.example.fileintegration.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@RestController
@RequestMapping("/files")
public class FileController {

    @PostMapping("/create")
    public String createFile(@RequestParam String fileName, @RequestParam String content) throws IOException {
        File file = new File("taskdata/input" + File.separator + fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
        return "File created successfully.";
    }

    @GetMapping("/read")
    public String readFiles() throws IOException {
        File outputFile = new File("taskdata/output" + File.separator + "odnotowane_pliki.txt");
        StringBuilder result = new StringBuilder();

        if (outputFile.exists()) {
            try (Scanner scanner = new Scanner(outputFile)) {
                while (scanner.hasNextLine()) {
                    result.append(scanner.nextLine()).append("\n");
                }
            }
        } else {
            result.append("Output file does not exist.");
        }

        return result.toString();
    }
}

