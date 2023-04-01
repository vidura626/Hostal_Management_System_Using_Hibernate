package entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Table(name = "full name")
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name {
    String fName;
    String mName;
    String lName;
}
