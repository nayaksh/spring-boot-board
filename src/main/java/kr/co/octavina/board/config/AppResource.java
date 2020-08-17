package kr.co.octavina.board.config;

import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

@Configuration
@Getter
@Validated
public class AppResource {

    @NonNull
    public final String storagePath;

    public AppResource(@Value("${application.storage-path}") final String storagePath) throws IOException {
        Files.createDirectories(Paths.get(storagePath));
        this.storagePath = storagePath;
    }
}