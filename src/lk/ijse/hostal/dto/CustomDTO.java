package lk.ijse.hostal.dto;

import lk.ijse.hostal.entity.embedded.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Getter
public class CustomDTO {
    String reservationId;
    Name studentName;
    double remainingAmount;
    String roomId;
    RoomDTO.RoomType roomType;
}
