package lk.ijse.hostal.dto;

import lk.ijse.hostal.dto.embedded.Address;
import lk.ijse.hostal.dto.embedded.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDetailsDTO {
    //    Want to add an image using web cam and QR Code generator with email
    private int id;
    private String username;
    private String name;
    private String email;
    private String password;
}
