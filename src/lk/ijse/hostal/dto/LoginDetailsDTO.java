package lk.ijse.hostal.dto;

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
public class LoginDetailsDTO {
    @Id
    private int id;

    //    Want to add an image using web cam and QR Code generator with email
    String name;

    @ElementCollection
    @CollectionTable(
            name = "adminAddresses",
            joinColumns = @JoinColumn(name = "userId")
    )
    List<Address> addresses;

    String email;

    String username;
    String password;

}
