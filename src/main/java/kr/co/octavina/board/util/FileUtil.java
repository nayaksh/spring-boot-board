package kr.co.octavina.board.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FileUtil {

    public static final String PATH_SEPARATOR = "/";

    public static String createPath() throws IOException {
        String timeString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm"));
        return PATH_SEPARATOR + timeString.replace("-",PATH_SEPARATOR) + PATH_SEPARATOR;
    }

    public static String createPathWithUniqueFileName() throws IOException {
        return createPath() + UUID.randomUUID().toString();
    }

    public static String createUniqueFileName() throws IOException {
        return UUID.randomUUID().toString();
    }
}
