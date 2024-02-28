package db.last.webapp.models;

import db.last.webapp.models.idGenerator.CustomIdGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(generator = "album_id")
    @GenericGenerator(
            name = "album_id",
            type = CustomIdGenerator.class,
            parameters = {@Parameter(name = "prefix", value = "Album")})
    private String id;
    private String name;
    @ManyToOne
    private Artist artist;
    @ManyToMany
    private List<Song> songs = new ArrayList<>();
}
