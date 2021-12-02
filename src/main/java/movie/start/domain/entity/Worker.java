package movie.start.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@Table(name = "WORKERS")
public abstract class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workerId;

    private String name;
    private LocalDate birthday;

    public Worker(String name, LocalDate birthday){
        this.name = name;
        this.birthday = birthday;
    }

    @OneToMany(mappedBy = "worker")
    private List<MovieWorker> movieWorkers = new ArrayList<>();

    public void addMovieWorker(MovieWorker movieWorker) {
        this.movieWorkers.add(movieWorker);
        if (movieWorker.getWorker() != this) {
            movieWorker.setWorker(this);
        }
    }

    public String display() {
        return "Worker{" +
                "workerId=" + workerId +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
