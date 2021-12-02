package movie.start.DAO;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import movie.start.domain.DTO.SeatDTO;
import movie.start.domain.DTO.TicketDTO;
import movie.start.domain.entity.*;
import movie.start.domain.enumType.TicketStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

public class TicketDAO {
    EntityManager em;
    EntityTransaction tx;
    JPAQueryFactory query;

    public TicketDAO(EntityManager em){
        this.em =em;
        this.tx = em.getTransaction();
        query = new JPAQueryFactory(em);
    }

    public Ticket createTicket(Long userId, Long screenId){
        try{
            tx.begin();
            User user = em.find(User.class, userId);
            Screen screen = em.find(Screen.class, screenId);
            Ticket ticket = new Ticket();
            ticket.setUser(user);
            ticket.setScreen(screen);
            ticket.setStatus(TicketStatus.예매);
            em.persist(ticket);
            tx.commit();
            return ticket;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public Ticket cancelTicket(Long ticketId){
        try{
            tx.begin();
            Ticket ticket = em.find(Ticket.class, ticketId);
            ticket.setStatus(TicketStatus.취소);
            tx.commit();
            return ticket;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public TicketDTO readTicket(Long ticketId){
        try{
            tx.begin();
            QTicket ticket = QTicket.ticket;
            QUser user = QUser.user;
            QScreen screen = QScreen.screen;
            QTheater theater = QTheater.theater;
            QMovie movie = QMovie.movie;
            QTicketSeat ticketSeat = QTicketSeat.ticketSeat;
            QSeat seat = QSeat.seat;

            TicketDTO ticketDTO = query
                    .from(ticket)
                    .join(ticket.user,user)
                    .join(ticket.screen,screen)
                    .join(screen.theater,theater)
                    .join(screen.movie,movie)
                    .leftJoin(ticket.ticketSeats,ticketSeat)
                    .leftJoin(ticketSeat.seat,seat)
                    .where(ticket.ticketId.eq(ticketId))
                    .transform(groupBy(ticket.ticketId).list(Projections.fields(
                            TicketDTO.class,
                            ticket.ticketId,ticket.status.as("ticketStatus"),ticket.cancelTime,
                            user.userId, user.name.as("userName"),
                            screen.screenId,screen.startTime, screen.endTime,
                            movie.movieId, movie.title, movie.genre,
                            theater.theaterId, theater.name.as("TheaterName"), theater.floor,
                            list(Projections.fields(SeatDTO.class, seat.seatId,seat.seatColumn,seat.seatRow, seat.status)).as("seats")
                    ))).get(0);
            tx.commit();
            return ticketDTO;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

}
