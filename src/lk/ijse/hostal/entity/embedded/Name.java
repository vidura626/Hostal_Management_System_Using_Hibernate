package lk.ijse.hostal.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
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
