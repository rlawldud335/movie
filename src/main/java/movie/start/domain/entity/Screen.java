package movie.start.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SCREENS")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screenId;

    @OneToMany(mappedBy = "screen")
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="THEATER_ID")
    private Theater theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Screen(Theater theater, Movie movie, LocalDateTime startTime, LocalDateTime endTime){
        this.theater = theater;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        if (ticket.getScreen() != this) {
            ticket.setScreen(this);
        }
    }

    public void setTheater(Theater theater) {
        if (this.theater != null) {
            this.theater.getScreens().remove(this);
        }
        this.theater = theater;
        if (!theater.getScreens().contains(this)) {
            theater.getScreens().add(this);
        }
    }

    public String display() {
        return "Screen{" +
                "screenId=" + screenId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
