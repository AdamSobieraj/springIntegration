package com.example.fileintegration.task;

import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CustomFileWritingMessageHandler extends AbstractMessageHandler {

    private final String outputFilePath;

    public CustomFileWritingMessageHandler(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    @Override
    protected void handleMessageInternal(Message<?> message) {
        Object payload = message.getPayload();
        if (payload instanceof File file) {
            writeToFile(file.getName());
        } else {
            throw new IllegalArgumentException("Bad Payload must be of type java.io.File");
        }
    }

    private void writeToFile(String fileName) {
        File outputFile = new File(outputFilePath);
        try (FileWriter writer = new FileWriter(outputFile, true)) {
            writer.write(fileName + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
