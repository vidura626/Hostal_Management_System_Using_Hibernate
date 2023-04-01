package lk.ijse.hostal.entity;

import entity.embedded.Address;
import entity.embedded.Name;
import lk.ijse.hostal.entity.embedded.Contact;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    String id;
    @Embedded
    Name name;
    @ElementCollection
    @CollectionTable(
            name = "addressess",
            joinColumns = @JoinColumn(name = "StudentId")
    )
    List<Address> addresses;

    @ElementCollection
    @CollectionTable(
            name = "Contacts",
            joinColumns = @JoinColumn(name = "StudentId")
    )
    List<Contact> contact;
    Date dob;
    Gender gender;

    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL)
    List<Reservation> reservations;
}
