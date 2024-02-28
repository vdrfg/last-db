package db.last.webapp.models;

import db.last.webapp.models.idGenerator.IdPrefixGenerator;
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
  @GeneratedValue(generator = "prefixGenerator")
  @GenericGenerator(
      name = "prefixGenerator",
      type = IdPrefixGenerator.class,
      parameters = {@Parameter(name = "prefixValue", value = "Artist")})
  private String id;

  private String name;
  @OneToMany private List<Album> albums = new ArrayList<>();
}
