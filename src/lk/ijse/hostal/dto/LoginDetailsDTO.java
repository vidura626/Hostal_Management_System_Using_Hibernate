package lk.ijse.hostal.dto;

import lk.ijse.hostal.entity.embedded.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDetailsDTO {
    private int id;
    //    Want to add an image using web cam and QR Code generator with email
    String name;
    List<Address> addresses;
    String email;
    String username;
    String password;

}
