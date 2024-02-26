package db.last.webapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    private List<Album> albums = new ArrayList<>();
    @ManyToOne
    private Artist artist;
    @OneToMany
    private List<Scrobble> scrobbles = new ArrayList<>();
}
