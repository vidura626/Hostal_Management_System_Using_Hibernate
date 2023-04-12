package lk.ijse.hostal.controller.TM;

import com.jfoenix.controls.JFXButton;
import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationTM {
    private String res_id;
    private Student studentId;
    private Room room;
    private Date fromDate;
    private Date toDate;
    private Reservation.Status status;
    private JFXButton update;
    private JFXButton delete;

}
