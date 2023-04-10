package lk.ijse.hostal.dto.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Name {
    private String fName;
    private String mName;
    private String lName;

    @Override
    public String toString() {
        return fName.concat(" ").concat(mName).concat(" ").concat(lName);
    }
}
