package lk.ijse.hostal.entity;

import lk.ijse.hostal.entity.embedded.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDetails {
    @Id
    private int id;
    /*
        QR Code generator with email
    */
    String name;

    @ElementCollection
    @CollectionTable(
            name = "adminAddresses",
            joinColumns = @JoinColumn(name = "userId")
    )
    private List<Address> addresses;

    private String email;

    private String username;
    private String password;

}
