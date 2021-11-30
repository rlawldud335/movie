package movie.start.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movie.start.domain.enumType.SeatStatus;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SEATS")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    private Integer seatColumn;
    private Integer seatRow;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    public Seat(Integer seatColumn, Integer seatRow, SeatStatus status){
        this.seatColumn = seatColumn;
        this.seatRow = seatRow;
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="THEATER_ID")
    private Theater theater;

    public void setTheater(Theater theater) {
        if (this.theater != null) {
            this.theater.getSeats().remove(this);
        }
        this.theater = theater;
        if (!theater.getSeats().contains(this)) {
            theater.getSeats().add(this);
        }
    }
}
