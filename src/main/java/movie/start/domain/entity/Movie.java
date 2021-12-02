package movie.start.domain.entity;

import lombok.*;
import movie.start.domain.enumType.MovieGenre;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MOVIES")
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String title;
    private LocalDate releaseTime;

    @Enumerated(EnumType.STRING)
    private MovieGenre genre;

    private Integer runningTime;

    @OneToMany(mappedBy = "movie")
    private List<Screen> screens = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<MovieWorker> movieWorkers = new ArrayList<>();

    public Movie(String title, LocalDate releaseTime, MovieGenre genre, Integer runningTime){
        this.title = title;
        this.releaseTime = releaseTime;
        this.genre = genre;
        this.runningTime = runningTime;
    }

    public void addMovieWorker(MovieWorker movieWorker) {
        this.movieWorkers.add(movieWorker);
        if (movieWorker.getMovie() != this) {
            movieWorker.setMovie(this);
        }
    }

    public void addScreen(Screen screen){
        this.screens.add(screen);
        if (screen.getMovie() != this) {
            screen.setMovie(this);
        }
    }

    public String display() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", releaseTime=" + releaseTime +
                ", genre=" + genre +
                ", runningTime=" + runningTime +
                '}';
    }
}
