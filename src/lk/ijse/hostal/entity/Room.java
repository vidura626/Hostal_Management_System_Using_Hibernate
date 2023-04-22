package lk.ijse.hostal.entity;

import lk.ijse.hostal.dto.RoomDTO;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Room {
    @Id
    String room_type_id;
    @Enumerated(EnumType.STRING)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    RoomDTO.RoomType type;
    double key_money;
    int qty;
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Reservation> reservations;

    @Override
    public String toString() {
        return room_type_id.concat(" ").concat(type.toString());
    }
}