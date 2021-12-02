package movie.start.service;

import movie.start.DAO.*;
import movie.start.domain.DTO.ScreenDTO;
import movie.start.domain.DTO.SeatDTO;
import movie.start.domain.DTO.TicketDTO;
import movie.start.domain.entity.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

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

    //1. 값타입 사용 Address

    //2. 사용자 정보 수정
    public void updateUser(User user){
        System.out.println("사용자 정보 수정");
        User uuser = userDAO.updateUser(user);
        System.out.println(uuser.display());
        System.out.println();
    }

    //3. 영화정보조회1
    public void readMovie(Long movieId){
        System.out.println("영화 정보 조회 1");
        Movie movie = movieDAO.readMovie(movieId);
        System.out.println(movie.display());
        System.out.println();
    }

    //4. 영화정보조회2
    public void findMovieWithWorkerOpeningDateRunningTime(String directorName, String actorName, LocalDate openingDate){
        System.out.println("영화 정보 조회 2");
        List<Movie> movies = movieDAO.findMovieWithWorkerOpeningDateRunningTime(directorName, actorName, openingDate);
        for(Movie m : movies){
            System.out.println(m.display());
        }
        System.out.println();
    }

    //5. 영화정보조회3
    public void readAllMovie(int pageNum){
        System.out.println("영화 정보 조회 3");
        List<Movie> movies = movieDAO.readAllMovie(pageNum);
        for(Movie m : movies){
            System.out.println(m.display());
        }
        System.out.println();
    }

    //6. 상영정보조회
    public void readAllScreen(){
        System.out.println("상영정보조회");
        List<ScreenDTO> screens = screenDAO.readAllScreen();
        for(ScreenDTO s : screens){
            System.out.println(s.display());
        }
        System.out.println();
    }

    //7. 예매
    public void createTicket(Long screenId, Long userId, Long[] seatIds){
        System.out.println("예매하기");
        //티켓 생성하기
        Ticket ticket = ticketDAO.createTicket(userId, screenId);
        //티켓 좌석 생성하기
        ticketSeatDAO.bulkCreateTicketSeat(ticket.getTicketId(), seatIds);
        //결과 출력
        System.out.println("예매완료");
    }

    //8. 예매취소
    public void cancelTicket(Long ticketId){
        System.out.println("예매취소");
        Ticket ticket = ticketDAO.cancelTicket(ticketId);
        System.out.println(ticket.display());
        System.out.println();
    }

    //9. 예매정보(영화티켓)
    public void readTicket(Long ticketId){
        System.out.println("예매정보 (영화티켓)");
        TicketDTO ticket =ticketDAO.readTicket(ticketId);
        System.out.println("ticketId : "+ticket.getTicketId());
        System.out.println("ticketStatus : "+ticket.getTicketStatus());
        System.out.println("ticketCancelTime : "+ticket.getCancelTime());
        System.out.println("UserName : "+ticket.getUserName());
        System.out.println("MovieTitle : "+ticket.getTitle());
        System.out.println("TheaterName : "+ticket.getTheaterName());
        System.out.println("TheaterFloor : "+ticket.getFloor());
        System.out.println("ScreenStartTime : "+ticket.getStartTime());
        System.out.println("ScreenEndTime : "+ticket.getEndTime());
        for (SeatDTO seat:ticket.getSeats()){
            System.out.println("SeatCol : "+seat.getSeatColumn());
            System.out.println("SeatRow : "+seat.getSeatRow());
            System.out.println("SeatStatus : "+seat.getStatus());
            System.out.println("----");
        }
        System.out.println();
    }

    //10.영속성 전이를 이용한 삭제
    public void removeUser(Long userId){
        System.out.println("영속성 전이를 이용한 삭제");
        userDAO.deleteUser(userId);
        System.out.println("삭제완료");
    }

}
