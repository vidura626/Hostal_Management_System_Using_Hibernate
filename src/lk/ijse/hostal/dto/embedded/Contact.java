package lk.ijse.hostal.dto.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contact {
    private String description;
    private String number;
}
