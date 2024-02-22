package db.last.webapp.services;

import db.last.webapp.models.Scrobble;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

@Service
public class FileService {

    public List<Scrobble> csvToScrobbles(String filePath) {
        Path path = Paths.get(filePath);
        List<String> csvLines;

        try {
            csvLines = Files.readAllLines(path);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return csvLines.stream()
                .map(line -> line.split(","))
                .map(array -> new Scrobble(array[0], array[1], array[2], stringToTimestamp(array[3]))).toList();
    }

    private Timestamp stringToTimestamp(String dateTime) {
        String pattern = "dd MMM yyyy HH:mm";
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().appendPattern(pattern)
                .toFormatter();
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(dateTime));
        return Timestamp.valueOf(localDateTime);
    }
}
