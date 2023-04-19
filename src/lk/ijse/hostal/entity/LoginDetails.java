package lk.ijse.hostal.entity;

import lk.ijse.hostal.entity.embedded.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDetails implements Serializable {
    @Id
    private int id;
    private String username;
    /*
        QR Code generator with email
    */
    String name;
    private String email;
    private String password;
}
