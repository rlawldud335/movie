package movie.start.service;

import movie.start.DAO.*;
import movie.start.domain.entity.*;
import movie.start.domain.enumType.MovieGenre;
import movie.start.domain.enumType.MovieWorkerRoleType;
import movie.start.domain.enumType.SeatStatus;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;

//0. 사전데이터 입력하기
public class TestDataInput {

    MovieDAO movieDAO;
    MovieWorkerDAO movieWorkerDAO;
    ScreenDAO screenDAO;
    SeatDAO seatDAO;
    TheaterDAO theaterDAO;
    TicketDAO ticketDAO;
    TicketSeatDAO ticketSeatDAO;
    UserDAO userDAO;
    WorkerDAO workerDAO;

    public TestDataInput(EntityManager em) {
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

    public void InputData(){
        //영화 생성
        Movie[] movies = new Movie[4];
        movies[0] = movieDAO.createMovie("영화1", LocalDate.parse("2021-11-11"), MovieGenre.드라마, 120);
        movies[1] = movieDAO.createMovie("영화2",LocalDate.parse("2021-10-11"), MovieGenre.사극, 100);
        movies[2] = movieDAO.createMovie("영화3",LocalDate.parse("2021-09-11"), MovieGenre.로맨스, 200);
        movies[3] = movieDAO.createMovie("영화4",LocalDate.parse("2021-08-11"), MovieGenre.액션, 150);

        //영화의 감독과 배우 생성
        Director[] directors = new Director[4];
        Actor[][] actors = new Actor[4][5];
        for(int i=1; i<=4; i++){
            directors[i-1] = workerDAO.CreateDirector("감독"+i, LocalDate.parse("1990-01-01"), "서울특별시");
        }
        for(int i=1 ;i<=4; i++){
            for(int j=1; j<=5; j++){
                actors[i-1][j-1] =workerDAO.CreateActor("배우"+i+"_"+j, LocalDate.parse("1990-01-01"), 180, "인스타주소");
            }
        }

        //영화의 연출,출연자 생성
        for(int i=0; i<4; i++){
            movieWorkerDAO.createMovieWorker(movies[i], directors[i], MovieWorkerRoleType.감독);
            for(int j=0; j<5; j++){
                if (j<2){ movieWorkerDAO.createMovieWorker(movies[i], actors[i][j], MovieWorkerRoleType.주연);}
                else { movieWorkerDAO.createMovieWorker(movies[i], actors[i][j], MovieWorkerRoleType.조연);}
            }
        }

        //상영관 생성
        Theater theater1 = theaterDAO.createTheater("1상영관",1);
        Theater theater2 = theaterDAO.createTheater("2상영관",2);

        //각 상영관에 좌석 생성
        Seat[] th1seats = new Seat[10];
        Seat[] th2seats = new Seat[10];
        for(int row=1, i=0,j=0; row<=2; row++){
            for(int col=1; col<=5; col++){
                th1seats[i++] = seatDAO.CreateSeat(theater1,col,row, SeatStatus.가용);
                th2seats[j++] = seatDAO.CreateSeat(theater2,col,row, SeatStatus.가용);
            }
        }

        //사용자 2명 생성
        User user1 = userDAO.createUser("사용자1",20,"city","street","zipcode");
        User user2 = userDAO.createUser("사용자2",30,"city","street","zipcode");

        //상영 생성
        Screen[] screens = new Screen[6];
        screens[0]=screenDAO.createScreen(theater1,movies[0], LocalDateTime.parse("2021-11-30T12:00:00"));
        screens[1]=screenDAO.createScreen(theater1,movies[0], LocalDateTime.parse("2021-11-30T15:00:00"));
        screens[2]=screenDAO.createScreen(theater1,movies[0], LocalDateTime.parse("2021-11-30T18:00:00"));
        screens[3]=screenDAO.createScreen(theater2,movies[0],LocalDateTime.parse("2021-11-30T12:00:00"));
        screens[4]=screenDAO.createScreen(theater2,movies[0],LocalDateTime.parse("2021-11-30T15:00:00"));
        screens[5]=screenDAO.createScreen(theater2,movies[0],LocalDateTime.parse("2021-11-30T18:00:00"));

        //예매 생성
        Ticket ticket1 = ticketDAO.createTicket(user1.getUserId(),screens[0].getScreenId());
        Ticket ticket2 =ticketDAO.createTicket(user2.getUserId(),screens[0].getScreenId());

        //예매한 티켓의 좌석 예매하기
        ticketSeatDAO.createTicketSeat(ticket1.getTicketId(), th1seats[0].getSeatId());
        ticketSeatDAO.createTicketSeat(ticket1.getTicketId(), th1seats[1].getSeatId());
        ticketSeatDAO.createTicketSeat(ticket2.getTicketId(), th1seats[2].getSeatId());
        ticketSeatDAO.createTicketSeat(ticket2.getTicketId(), th1seats[3].getSeatId());
    }
}
