package movie.start.domain.DTO;

import lombok.*;
import movie.start.domain.enumType.MovieGenre;
import movie.start.domain.enumType.SeatStatus;
import movie.start.domain.enumType.TicketStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    //ticket
    private Long ticketId;
    private TicketStatus ticketStatus;
    private LocalDateTime cancelTime;

    //user
    private Long userId;
    private String userName;

    //screen
    private Long screenId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    //movie
    private Long movieId;
    private String title;
    private MovieGenre genre;

    //theater
    private Long theaterId;
    private String TheaterName;
    private Integer floor;

    //seats
    private List<SeatDTO> seats = new ArrayList<>();

    public String display() {
        return "TicketDTO{" +
                "ticketId=" + ticketId +
                ", ticketStatus=" + ticketStatus +
                ", cancelTime=" + cancelTime +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", screenId=" + screenId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", movieId=" + movieId +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", theaterId=" + theaterId +
                ", TheaterName='" + TheaterName + '\'' +
                ", floor=" + floor +
                ", seats=" + seats +
                '}';
    }
}
