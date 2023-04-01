package lk.ijse.hostal.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Table(name = "address")
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    String houseNo;
    String streetName;
    String town;
    String postalCode;
}
