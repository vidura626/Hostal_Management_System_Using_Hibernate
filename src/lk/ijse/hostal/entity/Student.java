package lk.ijse.hostal.entity;

import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.entity.embedded.Contact;
import lk.ijse.hostal.entity.embedded.Name;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student {
    @Id
    String id;
    String nic;
    String email;

    /*
    Try to add image of student
    */

    @Embedded
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    Name name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(
            name = "addressess",
            joinColumns = @JoinColumn(name = "StudentId")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    List<Address> addresses;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection()
    @CollectionTable(
            name = "Contacts",
            joinColumns = @JoinColumn(name = "StudentId")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    List<Contact> contact;

    Date dob;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @CreationTimestamp
    Date joinedDate;
    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Reservation> reservations;

    @Override
    public String toString() {
        return id.concat(" : ").concat(name.getFName());
    }
}
