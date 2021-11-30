package movie.start;

import movie.start.service.CinemaService;
import movie.start.service.TestDataInput;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie");
        EntityManager em = emf.createEntityManager();

        //사전데이터 입력하기
        TestDataInput testDataInput = new TestDataInput(em);
        testDataInput.InputData();

        //Cinema Service Test
        System.out.println("====Cinema Service Start====");
        CinemaService cinemaService = new CinemaService(em);
        cinemaService.readUser(1L);
        System.out.println("====Cinema Service End====");

        em.close();
        emf.close();
    }
}
