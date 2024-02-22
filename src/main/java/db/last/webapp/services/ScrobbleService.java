package db.last.webapp.services;

import db.last.webapp.models.Scrobble;
import db.last.webapp.repositories.ScrobbleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class ScrobbleService {

    private ScrobbleRepository scrobbleRepository;

    public Scrobble save(Scrobble scrobble) {
        return scrobbleRepository.save(scrobble);
    }

    public List<Scrobble> saveBatch(List<Scrobble> scrobbles) {
        return scrobbleRepository.saveAll(scrobbles);
    }

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(dateTime));
        return Timestamp.valueOf(localDateTime);
    }
}
