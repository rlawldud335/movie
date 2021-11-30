package movie.start.DAO;

import movie.start.domain.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class WorkerDAO {
    EntityManager em ;
    EntityTransaction tx;

    public WorkerDAO(EntityManager em){
        this.em = em;
        this.tx = em.getTransaction();
    }

    public Actor CreateActor(String name, LocalDate birthday, Integer height, String instagramAddress){
        try{
            tx.begin();
            Actor actor = new Actor(name,birthday, height,instagramAddress);
            em.persist(actor);
            tx.commit();
            return actor;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public Director CreateDirector(String name, LocalDate birthday,String birthplace){
        try{
            tx.begin();
            Director director = new Director(name,birthday,birthplace);
            em.persist(director);
            tx.commit();
            return director;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }
}