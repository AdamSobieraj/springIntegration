package com.example.fileintegration.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;

import java.io.File;

@Configuration
public class FileTaskIntegrationConfiguration {

    @Bean
    IntegrationFlow fileIntegrationFlow(
            FileReadingMessageSource fileAdapter,
//            TaskFileTransformer transformer,
            CustomFileWritingMessageHandler outputFileHandler) {

        return IntegrationFlows.from(fileAdapter, config -> config.poller(Pollers.fixedDelay(1000)))
//                .transform(transformer, "transformFile")
                .handle(outputFileHandler)
                .get();
    }

    @Bean
    FileReadingMessageSource fileAdapter() {
        FileReadingMessageSource fileSource = new FileReadingMessageSource();
        fileSource.setDirectory(new File("taskdata/input"));

        return fileSource;
    }

    @Bean
    TaskFileTransformer transformer() {
        return new TaskFileTransformer();
    }

    @Bean
    public CustomFileWritingMessageHandler customFileWritingMessageHandler() {
        return new CustomFileWritingMessageHandler("taskdata/output/odnotowane_pliki.txt");
    }

}



