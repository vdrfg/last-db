package db.last.webapp.models;

import db.last.webapp.models.idGenerator.CustomIdGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Artist {

  @Id
  @GeneratedValue(generator = "artist_id")
  @GenericGenerator(
      name = "artist_id",
      type = CustomIdGenerator.class,
      parameters = {@Parameter(name = "prefix", value = "Artist")})
  private String id;

  private String name;
  @OneToMany private List<Album> albums = new ArrayList<>();
}
