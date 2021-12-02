package movie.start.DAO;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import movie.start.domain.entity.*;
import movie.start.domain.enumType.MovieGenre;
import movie.start.domain.enumType.MovieWorkerRoleType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;


public class MovieDAO {
    EntityManager em;
    EntityTransaction tx;
    JPAQueryFactory query;

    public MovieDAO(EntityManager em) {
        this.em = em;
        this.tx = em.getTransaction();
        query = new JPAQueryFactory(em);
    }

    public Movie createMovie(String title, LocalDate releaseTime, MovieGenre genre, Integer runningTime) {
        try {
            tx.begin();
            Movie movie = new Movie(title, releaseTime, genre, runningTime);
            em.persist(movie);
            tx.commit();
            return movie;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    public Movie readMovie(Long movieId) {
        try{
            QMovie qMovie = new QMovie("m");
            QMovieWorker qMovieWorker = new QMovieWorker("mw");
            QWorker qWorker = new QWorker("w");
            QActor qActor = QActor.actor;

            // 영화 조회 SELECT
            Movie movie = query.selectFrom(qMovie)
                    .join(qMovie.movieWorkers, qMovieWorker).fetchJoin()
                    .join(qMovieWorker.worker, qWorker).fetchJoin()
                    .join(qMovieWorker.worker, qActor._super)
                    .where(qMovie.movieId.eq(movieId))
                    .distinct()
                    .fetchOne();

            return movie;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public List<Movie> readAllMovie(int pageNum) {
        try {
            QMovie qMovie = new QMovie("m");
            QMovieWorker qMovieWorker = new QMovieWorker("mw");
            QWorker qWorker = new QWorker("w");

            // 영화 조회 SELECT
            List<Movie> movies = query.selectFrom(qMovie)
                    .join(qMovie.movieWorkers, qMovieWorker).fetchJoin()
                    .join(qMovieWorker.worker, qWorker).fetchJoin()
                    .offset(pageNum* 2L).limit(2)
                    .fetch();
            return movies;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    public List<Movie> findMovieWithWorkerOpeningDateRunningTime(String directorName, String actorName, LocalDate openingDate) {
        try {
            QMovie qMovie = new QMovie("m");
            QMovieWorker actorCasting = new QMovieWorker("mw1");
            QMovieWorker directorCasting = new QMovieWorker("mw2");
            QWorker actor = new QWorker("wk1");
            QWorker director = new QWorker("wk2");

            List<Movie> movies = query.selectFrom(qMovie)
                    .join(qMovie.movieWorkers, actorCasting)
                    .join(actorCasting.worker, actor)
                    .join(qMovie.movieWorkers, directorCasting)
                    .join(directorCasting.worker, director)
                    .on(actor.workerId.ne(director.workerId))
                    .where(directorNameEq(directorCasting, director, directorName), actorNameEq(actorCasting, actor, actorName), releaseInYear(qMovie, openingDate.getYear()))
                    .distinct()
                    .fetch();
            return movies;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    private BooleanExpression directorNameEq(QMovieWorker directorCasting, QWorker director, String directorName) {
        if (directorName == null || directorName.equals("")) {
            return null;
        }
        return director.name.eq(directorName).and(directorCasting.roleType.eq(MovieWorkerRoleType.감독));
    }

    private BooleanExpression actorNameEq(QMovieWorker actorCasting, QWorker actor, String actorName) {
        if (actorName == null || actorName.equals("")) {
            return null;
        }
        return actor.name.eq(actorName).and(actorCasting.roleType.ne(MovieWorkerRoleType.감독));
    }

    private BooleanExpression releaseInYear(QMovie movie, int year) {
        if (year <= 0) {
            return null;
        }
        return movie.releaseTime.goe(LocalDate.of(year, 1, 1))
                .and(movie.releaseTime.loe(LocalDate.of(year, 12, 31)));
    }
}
