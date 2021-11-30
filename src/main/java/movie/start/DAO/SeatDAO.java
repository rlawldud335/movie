package movie.start.DAO;

import movie.start.domain.entity.Seat;
import movie.start.domain.entity.Theater;
import movie.start.domain.enumType.SeatStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SeatDAO {
    EntityManager em;
    EntityTransaction tx;

    public SeatDAO(EntityManager em){
        this.em =em;
        this.tx = em.getTransaction();
    }

    public Seat CreateSeat(Theater theater, Integer col, Integer row, SeatStatus status){
        try{
            tx.begin();
            Seat seat = new Seat(col, row,status);
            seat.setTheater(theater);
            em.persist(seat);
            tx.commit();
            return seat;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

}
