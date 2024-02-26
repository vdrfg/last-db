package db.last.webapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue()
    private Long id;
    private String name;
    @ManyToOne
    private Artist artist;
    @ManyToMany
    private List<Song> songs = new ArrayList<>();

    public Album(Artist artist, String name) {
        this.artist = artist;
        this.name = name;
    }
}
