package lk.ijse.hostal.entity;

import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.entity.embedded.Name;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Custom {
    String reservationId;
    Name studentName;
    double remainingAmount;
    String roomId;
    RoomDTO.RoomType roomType;
}
