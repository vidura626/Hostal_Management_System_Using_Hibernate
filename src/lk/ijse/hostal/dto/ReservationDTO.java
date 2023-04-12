package lk.ijse.hostal.dto;

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
public class ReservationDTO {
    private String res_id;
    private Date fromDate;
    private Date toDate;
    private double keyMoneyAmount;
    private double remainingAmount;
    private Reservation.Status status;
    private Student studentId;
    private Room room;
}
