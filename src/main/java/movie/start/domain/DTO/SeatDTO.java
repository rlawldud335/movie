package movie.start.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movie.start.domain.enumType.SeatStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {
    private Long seatId;
    private Integer seatColumn;
    private Integer seatRow;
    private SeatStatus status;

    public String display() {
        return "SeatDTO{" +
                "seatId=" + seatId +
                ", seatColumn=" + seatColumn +
                ", seatRow=" + seatRow +
                ", status=" + status +
                '}';
    }
}
