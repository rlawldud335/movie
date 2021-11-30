package movie.start.DAO;

import com.querydsl.jpa.impl.JPAQueryFactory;
import movie.start.domain.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

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

    public void readAllScreen() {
        try {
            tx.begin();
            JPAQueryFactory query = new JPAQueryFactory(em);
            QScreen qScreen = new QScreen("s");
            List<Screen> screens = query.selectFrom(qScreen)
                    .fetch();

            System.out.println(screens.size());
            for (Screen s: screens) {
                System.out.println(s.getMovie().getTitle() + " " + s.getStartTime() + " " + s.getEndTime());
//				System.out.println("	전체 좌석");
//				for (Seat seat: s.getTheater().getSeats()) {
//					System.out.println("	행: " + seat.getSeatRow() + " 열:" + seat.getSeatColumn());
//				}
//				System.out.println("	사용 가능 좌석");
//				for (Ticket ticket: s.getTickets()) {
//					for (TicketSeat ticketSeat: ticket.getTicketSeats()) {
//						System.out.println("	행: " +ticketSeat.getSeat().getSeatRow() + " 열:" + ticketSeat.getSeat().getSeatColumn());
//					}
//				}
            }
            screens.stream().forEach(s ->
                    System.out.println(s.getMovie().getTitle() + " " + s.getStartTime() + " " + s.getEndTime()));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println(e);
            throw e;
        }
    }

    public Screen updateScreen(Long screenId, Screen screen) {
        try {
            tx.begin();
            Screen findScreen = em.find(Screen.class, screenId);
            findScreen.setMovie(screen.getMovie());
            findScreen.setStartTime(screen.getStartTime());
            findScreen.setEndTime(screen.getEndTime());
            findScreen.setTheater(screen.getTheater());
            findScreen.setTickets(screen.getTickets());
            tx.commit();
            return screen;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
}
