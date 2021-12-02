package movie.start.domain.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipCode);
    }

    public String display() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}

