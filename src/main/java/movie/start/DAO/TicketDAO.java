package movie.start.DAO;

import movie.start.domain.entity.Screen;
import movie.start.domain.entity.Ticket;
import movie.start.domain.entity.User;
import movie.start.domain.enumType.TicketStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TicketDAO {
    EntityManager em;
    EntityTransaction tx;

    public TicketDAO(EntityManager em){
        this.em =em;
        this.tx = em.getTransaction();
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

    public Ticket updateTicket(Ticket ticket){
        try{
            tx.begin();
            Ticket findTicket = em.find(Ticket.class, ticket.getTicketId());
            findTicket.setStatus(ticket.getStatus());
            findTicket.setUser(ticket.getUser());
            findTicket.setScreen(ticket.getScreen());
            tx.commit();
            return ticket;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public Ticket readTicket(Long ticketId){
        try{
            tx.begin();
            Ticket ticket = em.find(Ticket.class, ticketId);
            tx.commit();
            return ticket;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public void deleteTicket(Long ticketId){
        try{
            tx.begin();
            Ticket ticket = em.find(Ticket.class, ticketId);
            em.remove(ticket);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }
}
