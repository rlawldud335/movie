package movie.start.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TICKET_SEAT")
public class TicketSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketSeatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TICKET_ID")
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEAT_ID")
    private Seat seat;

    public void setTicket(Ticket ticket) {
        if (this.ticket != null) {
            this.ticket.getTicketSeats().remove(this);
        }
        this.ticket = ticket;
        if (!ticket.getTicketSeats().contains(this)) {
            ticket.getTicketSeats().add(this);
        }
    }
}
