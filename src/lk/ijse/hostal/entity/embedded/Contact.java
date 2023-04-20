package lk.ijse.hostal.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Table
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Contact {
    String description;
    String number;

    @Override
    public String toString() {
        return description
                .concat(" : ")
                .concat(number);
    }
}
