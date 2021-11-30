package movie.start.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movie.start.domain.enumType.MovieWorkerRoleType;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MOVIE_WORKER")
public class MovieWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieWorkerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MOVIE_ID")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORKER_ID")
    private Worker worker;

    @Enumerated(EnumType.STRING)
    private MovieWorkerRoleType roleType;

    public void setMovie(Movie movie) {
        if (this.movie != null) {
            this.movie.getMovieWorkers().remove(this);
        }
        this.movie = movie;
        if (!movie.getMovieWorkers().contains(this)) {
            movie.getMovieWorkers().add(this);
        }
    }

    public void setWorker(Worker worker) {
        if (this.worker != null) {
            this.worker.getMovieWorkers().remove(this);
        }
        this.worker = worker;
        if (!worker.getMovieWorkers().contains(this)) {
            worker.getMovieWorkers().add(this);
        }
    }
}
