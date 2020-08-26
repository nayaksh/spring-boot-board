package kr.co.octavina.board.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
@Getter
public class AppResource {

    public final String storagePath;

    public AppResource(@Value("${application.storage-path}") String storagePath) throws Exception {
        if (StringUtils.isEmptyOrWhitespace(storagePath)) {
            throw new Exception("application.storage-path is null or blank!!");
        } else {
            storagePath = storagePath.trim();
        }

        Files.createDirectories(Paths.get(storagePath));
        this.storagePath = storagePath;
    }
}