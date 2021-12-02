package movie.start;

import movie.start.domain.entity.User;
import movie.start.service.CinemaService;
import movie.start.service.TestDataInput;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie");
        EntityManager em = emf.createEntityManager();

        //사전데이터 입력하기
        TestDataInput testDataInput = new TestDataInput(em);
        testDataInput.InputData();

        //Cinema Service Test
        em.clear();
        CinemaService cinemaService = new CinemaService(em);
        System.out.println("====Cinema Service Start====");
        User user = em.find(User.class, 1L);
        user.setName("사용자10");
        em.clear();
        cinemaService.updateUser(user);
        em.clear();
        cinemaService.readMovie(1L);
        em.clear();
        cinemaService.findMovieWithWorkerOpeningDateRunningTime("감독1","배우1_1", LocalDate.of(2021,11,11));
        cinemaService.findMovieWithWorkerOpeningDateRunningTime(null,null, LocalDate.of(2021,11,11));
        em.clear();
        cinemaService.readAllMovie(0);
        cinemaService.readAllMovie(1);
        em.clear();
        cinemaService.readAllScreen();
        em.clear();
        Long[] seats = {7L,9L};
        cinemaService.createTicket(1L,1L,seats);
        em.clear();
        cinemaService.cancelTicket(1L);
        em.clear();
        cinemaService.readTicket(1L);
        em.clear();
        cinemaService.removeUser(1L);
        //create cancel remove JPA를 JPQL이나 QueryDSL로 바꿔야 하는지 물어보기!
        System.out.println("====Cinema Service End====");

        em.close();
        emf.close();
    }
}
