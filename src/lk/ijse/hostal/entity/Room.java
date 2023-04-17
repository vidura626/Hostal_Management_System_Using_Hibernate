package lk.ijse.hostal.entity;

import lk.ijse.hostal.dto.RoomDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    String room_type_id;
    @Enumerated(EnumType.STRING)
    RoomDTO.RoomType type;
    double key_money;
    int qty;
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Reservation> reservations;
}