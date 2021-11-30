package movie.start.service;

import movie.start.DAO.*;
import movie.start.domain.entity.Movie;
import movie.start.domain.entity.Screen;
import movie.start.domain.entity.Ticket;
import movie.start.domain.entity.User;

import javax.persistence.EntityManager;

public class CinemaService {

    MovieDAO movieDAO;
    MovieWorkerDAO movieWorkerDAO;
    ScreenDAO screenDAO;
    SeatDAO seatDAO;
    TheaterDAO theaterDAO;
    TicketDAO ticketDAO;
    TicketSeatDAO ticketSeatDAO;
    UserDAO userDAO;
    WorkerDAO workerDAO;

    public CinemaService(EntityManager em) {
        movieDAO = new MovieDAO(em);
        movieWorkerDAO = new MovieWorkerDAO(em);
        screenDAO = new ScreenDAO(em);
        seatDAO = new SeatDAO(em);
        theaterDAO = new TheaterDAO(em);
        ticketDAO = new TicketDAO(em);
        ticketSeatDAO = new TicketSeatDAO(em);
        userDAO = new UserDAO(em);
        workerDAO = new WorkerDAO(em);
    }

    //0. 회원정보 조회(Users)
    public void readUser(Long userId){
        System.out.println("0. 회원정보 조회");
        System.out.println("UserId : "+userId);
        User user = userDAO.readUser(userId);
        System.out.println("Name : "+ user.getName());
        System.out.println("Age : "+user.getAge());
        System.out.println("Address : "+user.getAddress().getCity()+" - "+user.getAddress().getStreet()+" - "+user.getAddress().getZipCode());
        System.out.println();
    }

    //1. 전체 상영 리스트 조회 (Screen + Movie)
    public void readScreens(){
        System.out.println("1. 전체 상영 목록 조회");
//        Screen[] screens = screenDAO.readAllScreen();
//        for(Screen scr : screens){
//            System.out.println(scr.toString());
//        }
        System.out.println();
    }

    //2. 영화 상세 조회 (Movie + MovieWorker + Worker + Actor + Director)
    public void readMovie(Long movieId){
        System.out.println("2. 영화 상세 조회");
        Movie movie = movieDAO.readMovie(movieId);
        System.out.println(movie.toString());
        System.out.println();
    }

    //3. 상영의 상영관과 좌석 조회(Screens + Theater + Seats + TicketSeat)
    public void readScreenSeats(Long screenId){
        System.out.println("3. 상영의 상영관과 좌석 조회");
    }

    //4. 특정 상영의 특정좌석들을 예매하기(Users + Tickets + TicketSeat)
    public void createTicket(Long screenId, Long userId, Long[] seatIds){
        System.out.println("4. 상영의 좌석들 예매");
        Ticket ticket = ticketDAO.createTicket(userId, screenId);
        System.out.println("TicketId : "+ticket.getTicketId());
        System.out.println("");

    }

    //5. 사용자의 예매 내역 조회히기 (Users + Tickets + TicketSeat+ Screens + Theaters + Seats)
    public void readTickets(Long userId){
        System.out.println("5. 사용자의 예매 내역 조회");
    }

    //6. 예매 취소하기 ( Tickets + TicketSeat+ Seats)
    public void cancelTicket(Long ticketId){
        System.out.println("6. 예매 취소하기");
    }

    //7. 영화검색하기 (Movies + MovieWorker + Worker + Actor + Director)
    public void findMovie(){
        System.out.println("7. 영화 검색하기");
    }

    //8. 배우가 참여한 작품 조회하기 (Movies + MovieWorker + Worker + Actor + Director)
    public void findWorker(){
        System.out.println("8. 배우가 참여한 작품 조회하기");
    }
}
