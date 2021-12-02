package movie.start.domain.entity;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DIRECTORS")
@DiscriminatorValue("DIRECTOR")
public class Director extends Worker{
    private String birthplace;

    public Director(String name, LocalDate birthday, String birthplace){
        super(name,birthday);
        this.birthplace =birthplace;
    }

    public String display() {
        return "Director{" +
                "birthplace='" + birthplace + '\'' +
                '}';
    }
}
