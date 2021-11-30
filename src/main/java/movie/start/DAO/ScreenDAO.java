package movie.start.DAO;

import movie.start.domain.entity.Movie;
import movie.start.domain.entity.Screen;
import movie.start.domain.entity.Theater;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class ScreenDAO {
    EntityManager em;
    EntityTransaction tx;

    public ScreenDAO(EntityManager em){
        this.em =em;
        this.tx = em.getTransaction();
    }

    public Screen createScreen(Theater theater, Movie movie, LocalDateTime startTime){
        try{
            tx.begin();
            Screen screen = new Screen();
            screen.setTheater(theater);
            screen.setMovie(movie);
            screen.setStartTime(startTime);
            LocalDateTime endTime = startTime.plusMinutes(movie.getRunningTime());
            screen.setEndTime(endTime);
            em.persist(screen);
            tx.commit();
            return screen;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public Screen[] readAllScreen(){
        try{
            tx.begin();
            Screen[] screens = new Screen[4];
            tx.commit();
            return screens;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }


}
