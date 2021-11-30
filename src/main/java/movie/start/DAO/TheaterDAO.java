package movie.start.DAO;

import movie.start.domain.entity.Theater;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TheaterDAO {
    EntityManager em;
    EntityTransaction tx;

    public TheaterDAO(EntityManager em){
        this.em = em;
        this.tx = em.getTransaction();
    }

    public Theater createTheater(String name, Integer floor){
        try{
            tx.begin();
            Theater theater = new Theater(name, floor);
            em.persist(theater);
            tx.commit();
            return theater;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public Theater updateTheater(Theater theater){
        try{
            tx.begin();
            Theater findTheater = em.find(Theater.class, theater.getTheaterId());
            findTheater.setName(theater.getName());
            findTheater.setFloor(theater.getFloor());
            tx.commit();
            return findTheater;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }
}
