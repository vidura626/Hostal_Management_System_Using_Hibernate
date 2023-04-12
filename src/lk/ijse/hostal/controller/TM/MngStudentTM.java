package lk.ijse.hostal.controller.TM;

import com.jfoenix.controls.JFXButton;
import lk.ijse.hostal.dto.embedded.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MngStudentTM {
    private String id;
    private String nic;
    private Name name;
    private String gender;
    private Date dob;
    private Date date;
    private JFXButton update;
    private JFXButton delete;
    private JFXButton more;
}
