package lk.ijse.hostal.controller.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewReservationForStdTM {
    private String id;
    private String room;
    private Date from;
    private Date to;
    private String status;
}
