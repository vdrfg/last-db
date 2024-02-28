package db.last.webapp.models;

import db.last.webapp.models.idGenerator.IdPrefixGenerator;
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
public class Song {
    @Id
    @GeneratedValue(generator = "prefixGenerator")
    @GenericGenerator(
            name = "prefixGenerator",
            type = IdPrefixGenerator.class,
            parameters = {@Parameter(name = "prefixValue", value = "Song")})
    private String id;
    private String name;
    @ManyToMany
    private List<Album> albums = new ArrayList<>();
    @ManyToOne
    private Artist artist;
}
