package movie.start.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "THEATERS")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theaterId;

    private String name;
    private Integer floor;

    public Theater(String name, Integer floor){
        this.name = name;
        this.floor = floor;
    }

    @OneToMany(mappedBy = "theater")
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "theater")
    private List<Screen> screens = new ArrayList<>();

    public void addScreen(Screen screen) {
        this.screens.add(screen);
        if (screen.getTheater() != this) {
            screen.setTheater(this);
        }
    }

    public void addSeat(Seat seat) {
        this.seats.add(seat);
        if (seat.getTheater() != this) {
            seat.setTheater(this);
        }
    }
}
