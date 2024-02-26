package db.last.webapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany
    private List<Album> albums = new ArrayList<>();

    public Artist(String name) {
        this.name = name;
    }
}
