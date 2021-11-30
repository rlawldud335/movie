package movie.start.DAO;

import movie.start.domain.entity.Movie;
import movie.start.domain.enumType.MovieGenre;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class MovieDAO {
    EntityManager em;
    EntityTransaction tx;

    public MovieDAO(EntityManager em){
        this.em =em;
        this.tx = em.getTransaction();
    }

    public Movie createMovie(String title, LocalDate releaseTime, MovieGenre genre, Integer runningTime){
        try{
            tx.begin();
            Movie movie = new Movie(title, releaseTime, genre, runningTime);
            em.persist(movie);
            tx.commit();
            return movie;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public Movie updateMovie(Movie movie){
        try{
            tx.begin();
            Movie findMovie = em.find(Movie.class, movie.getMovieId());
            findMovie.setTitle(movie.getTitle());
            findMovie.setReleaseTime(movie.getReleaseTime());
            findMovie.setGenre(movie.getGenre());
            findMovie.setRunningTime(movie.getRunningTime());
            tx.commit();
            return movie;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public Movie readMovie(Long movieId){
        try{
            tx.begin();
            Movie findMovie = em.find(Movie.class, movieId);
            tx.commit();
            return findMovie;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }
}
