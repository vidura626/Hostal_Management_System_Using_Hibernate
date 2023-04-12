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
    /*------------------------------------
    Try to use JSON like this
        {
            date from: xxxx-xx-xx
            date to  : xxxx-xx-xx
            Key money amount: xxx.xx
            paid Amount : xxx.xx
        }
    */
    private Date fromDate;
    private Date toDate;
    private double keyMoneyAmount;
    private double remainingAmount;
    @Enumerated(EnumType.STRING)
    private Status status;
    /*------------------------------------*/

    @ManyToOne
    private Student studentId;

    @ManyToOne
    private Room room;

    public enum Status {
        FULL, NOT_FULL
    }
}
