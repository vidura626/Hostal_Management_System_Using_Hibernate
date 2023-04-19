package lk.ijse.hostal.controller.TM;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MngUserTM {
    private int id;
    private String username;
    private String name;
    private String email;
    private String password;
    private JFXButton update;
    private JFXButton delete;
}
