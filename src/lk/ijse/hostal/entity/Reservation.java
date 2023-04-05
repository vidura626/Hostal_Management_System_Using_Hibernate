package lk.ijse.hostal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
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
    private double paidAmount;
    /*------------------------------------*/

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Student.class)
    private Student studentId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Room.class)
    private Room room;
}
