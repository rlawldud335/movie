package movie.start.domain.entity;

import lombok.*;
import movie.start.domain.enumType.SeatStatus;
import movie.start.domain.enumType.TicketStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TICKETS")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Column(updatable = false)
    private LocalDateTime createTime;
    @PrePersist
    public void before(){
        this.createTime = LocalDateTime.now();
    }


    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private LocalDateTime cancelTime;

    public void setStatus(TicketStatus status) {
        if(status.equals(TicketStatus.취소)){
            this.cancelTime = LocalDateTime.now();
            //예약되어있던 ticketSeats 를 모두 가용상태로 바꾸기
            for(TicketSeat ticketSeat: this.ticketSeats){
                ticketSeat.getSeat().setStatus(SeatStatus.가용);
            }
        }
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SCREEN_ID")
    private Screen screen;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<TicketSeat> ticketSeats = new ArrayList<>();

    public void setUser(User user) {
        if (this.user != null) {
            this.user.getTickets().remove(this);
        }
        this.user = user;
        if (!user.getTickets().contains(this)) {
            user.getTickets().add(this);
        }
    }

    public void setScreen(Screen screen) {
        if (this.screen != null) {
            this.screen.getTickets().remove(this);
        }
        this.screen = screen;
        if (!screen.getTickets().contains(this)) {
            screen.getTickets().add(this);
        }
    }

    public void addSeatTicket(TicketSeat ticketSeat) {
        this.ticketSeats.add(ticketSeat);
        if (ticketSeat.getTicket() != this) {
            ticketSeat.setTicket(this);
        }
    }

    public String display() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", createTime=" + createTime +
                ", status=" + status +
                ", cancelTime=" + cancelTime +
                '}';
    }
}
