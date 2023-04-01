package lk.ijse.hostal.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
