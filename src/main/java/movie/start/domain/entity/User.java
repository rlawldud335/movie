package movie.start.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private Integer age;

    @Embedded
    private Address address;

    public User(String name, Integer age, Address address){
        this.name = name;
        this.age = age;
        this.address =address;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        if (ticket.getUser() != this) {
            ticket.setUser(this);
        }
    }

    public String display() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
