package movie.start.DAO;

import movie.start.domain.entity.Movie;
import movie.start.domain.entity.MovieWorker;
import movie.start.domain.entity.Worker;
import movie.start.domain.enumType.MovieWorkerRoleType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MovieWorkerDAO {
    EntityManager em;
    EntityTransaction tx;

    public MovieWorkerDAO(EntityManager em){
        this.em =em;
        this.tx = em.getTransaction();
    }

    public MovieWorker createMovieWorker(Movie movie, Worker worker, MovieWorkerRoleType roleType){
        try{
            tx.begin();
            MovieWorker movieWorker = new MovieWorker();
            movieWorker.setMovie(movie);
            movieWorker.setWorker(worker);
            movieWorker.setRoleType(roleType);
            em.persist(movieWorker);
            tx.commit();
            return movieWorker;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }
}
