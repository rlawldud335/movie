package movie.start.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ACTORS")
@DiscriminatorValue("ACTOR")
public class Actor extends Worker{
    private Integer height;
    private String instagramAddress;

    public Actor(String name, LocalDate birthday, Integer height, String instagramAddress){
       super(name,birthday);
       this.height = height;
       this.instagramAddress = instagramAddress;
    }
}
