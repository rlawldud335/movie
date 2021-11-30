package movie.start.DAO;

import movie.start.domain.entity.Seat;
import movie.start.domain.entity.Ticket;
import movie.start.domain.entity.TicketSeat;
import movie.start.domain.enumType.SeatStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TicketSeatDAO {
    EntityManager em;
    EntityTransaction tx;

    public TicketSeatDAO(EntityManager em){
        this.em =em;
        this.tx = em.getTransaction();
    }

    public TicketSeat createTicketSeat(Ticket ticket, Seat seat){
        try{
            tx.begin();
            TicketSeat ticketSeat = new TicketSeat();
            ticketSeat.setTicket(ticket);
            ticketSeat.setSeat(seat);
            Seat findSeat = em.find(Seat.class, seat.getSeatId());
            findSeat.setStatus(SeatStatus.불가용);
            em.persist(ticketSeat);
            tx.commit();
            return ticketSeat;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public TicketSeat updateTicketSeat(TicketSeat ticketSeat){
        try{
            tx.begin();
            TicketSeat findTicketSeat = em.find(TicketSeat.class, ticketSeat.getTicketSeatId());
            findTicketSeat.setSeat(ticketSeat.getSeat());
            findTicketSeat.setTicket(ticketSeat.getTicket());
            tx.commit();
            return findTicketSeat;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public TicketSeat readTicketSeat(Long ticketSeatId){
        try{
            tx.begin();
            TicketSeat ticketSeat = em.find(TicketSeat.class, ticketSeatId);
            tx.commit();
            return ticketSeat;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public void deleteTicketSeat(Long ticketSeatId){
        try{
            tx.begin();
            TicketSeat ticketSeat = em.find(TicketSeat.class, ticketSeatId);
            em.remove(ticketSeat);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }
}
