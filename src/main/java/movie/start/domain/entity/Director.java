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
@Table(name = "DIRECTORS")
@DiscriminatorValue("DIRECTOR")
public class Director extends Worker{
    private String birthplace;
    public Director(String name, LocalDate birthday, String birthplace){
        super(name,birthday);
        this.birthplace =birthplace;
    }
}
