package movie.start.domain.DTO;

import lombok.*;
import movie.start.domain.entity.Seat;
import movie.start.domain.entity.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScreenDTO {
    private Long screenId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // movie
    private String title;

    // theater
    private String name;

    // 좌석
    private List<SeatDTO> seats = new ArrayList<>();

    public String display() {
        return "ScreenDTO{" +
                "screenId=" + screenId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", seats=" + seats +
                '}';
    }
}
