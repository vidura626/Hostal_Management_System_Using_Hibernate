package lk.ijse.hostal.dto;

import lk.ijse.hostal.entity.Reservation;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RequiredArgsConstructor
public class RoomDTO {
    @NonNull
    String room_type_id;
    @NonNull
    String type;
    @NonNull
    double key_money;
    @NonNull
    int qty;
    List<Reservation> reservations;

    public enum RoomType {
        NON_AC,NON_AC_FOOD,AC,AC_FOOD
    }

    @Override
    public String toString() {
        return room_type_id.concat(" : ").concat(type);
    }
}
