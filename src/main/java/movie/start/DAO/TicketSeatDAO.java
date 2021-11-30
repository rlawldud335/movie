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

    public TicketSeat createTicketSeat(Long ticketId, Long seatId){
        try{
            tx.begin();
            Ticket ticket = em.find(Ticket.class, ticketId);
            Seat seat = em.find(Seat.class, seatId);
            TicketSeat ticketSeat = new TicketSeat();
            ticketSeat.setTicket(ticket);
            ticketSeat.setSeat(seat);
            seat.setStatus(SeatStatus.불가용);
            em.persist(ticketSeat);
            tx.commit();
            return ticketSeat;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public TicketSeat[] bulkCreateTicketSeat(Long ticketId, Long[] seatIds){
        try{
            tx.begin();
            TicketSeat[] ticketSeats = new TicketSeat[seatIds.length];
            Ticket ticket = em.find(Ticket.class, ticketId);
            int index = 0;
            for(Long seatId: seatIds){
                Seat seat = em.find(Seat.class, seatId);
                ticketSeats[index] = new TicketSeat();
                ticketSeats[index].setTicket(ticket);
                ticketSeats[index].setSeat(seat);
                seat.setStatus(SeatStatus.불가용);
                em.persist(ticketSeats[index++]);
            }
            tx.commit();
            return ticketSeats;
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
