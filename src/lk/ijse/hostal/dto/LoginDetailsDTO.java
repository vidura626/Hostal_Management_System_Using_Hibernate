package lk.ijse.hostal.dto;

import lk.ijse.hostal.dto.embedded.Address;
import lk.ijse.hostal.dto.embedded.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDetailsDTO {
    private String id;
    //    Want to add an image using web cam and QR Code generator with email
    private Name name;
    private List<Address> addresses = new ArrayList<>();
    private String email;
    private String username;
    private String password;

}
