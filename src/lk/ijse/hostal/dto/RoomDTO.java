package lk.ijse.hostal.dto;

import lk.ijse.hostal.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {
String room_type_id;
    String type;
    double key_money;
    int qty;
    List<Reservation> reservations;

}
