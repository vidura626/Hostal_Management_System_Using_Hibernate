package lk.ijse.hostal.entity;

import entity.embedded.Name;
import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.entity.embedded.Contact;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    String id;
    String nic;
    String email;
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

    @CreationTimestamp
    Date joinedDate;
    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL)
    List<Reservation> reservations;
}
