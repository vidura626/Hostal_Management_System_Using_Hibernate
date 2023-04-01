package lk.ijse.hostal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    String room_type_id;
    String type;
    double key_money;
    int qty;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    List<Reservation> reservations;
}
