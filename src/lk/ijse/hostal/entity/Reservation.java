package lk.ijse.hostal.entity;

import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    String res_id;
    Date date;
    String status;

    @ManyToOne(fetch = FetchType.LAZY)
    Student studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    Room room;
}
