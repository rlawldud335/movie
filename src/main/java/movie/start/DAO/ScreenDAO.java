package movie.start.DAO;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import movie.start.domain.DTO.ScreenDTO;
import movie.start.domain.DTO.SeatDTO;
import movie.start.domain.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

public class ScreenDAO {
    EntityManager em;
    EntityTransaction tx;

    public ScreenDAO(EntityManager em) {
        this.em = em;
        this.tx = em.getTransaction();
    }

    public Screen createScreen(Theater theater, Movie movie, LocalDateTime startTime) {
        try {
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
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    public List<ScreenDTO> readAllScreen() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QScreen qScreen = QScreen.screen;
        QTheater qTheater = QTheater.theater;
        QMovie qMovie = QMovie.movie;
        QSeat qSeat = QSeat.seat;

        List<ScreenDTO> screens = query
                .from(qScreen)
                .join(qScreen.movie, qMovie)
                .join(qScreen.theater, qTheater)
                .join(qTheater.seats, qSeat)
                .transform(groupBy(qScreen.screenId).list(Projections.fields(
                        ScreenDTO.class,
                        qScreen.screenId,
                        qScreen.startTime,
                        qScreen.endTime,
                        qMovie.title,
                        qTheater.name,
                        list(Projections.fields(SeatDTO.class,
                                qSeat.seatId,
                                qSeat.seatColumn,
                                qSeat.seatRow,
                                qSeat.status)).as("seats")
                )));
        return screens;
    }

}
