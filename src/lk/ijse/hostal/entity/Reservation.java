package lk.ijse.hostal.entity;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private String res_id;
    private Date fromDate;
    private Date toDate;
    private double keyMoneyAmount;
    private double remainingAmount;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Student studentId;

    @ManyToOne
    private Room room;
}
