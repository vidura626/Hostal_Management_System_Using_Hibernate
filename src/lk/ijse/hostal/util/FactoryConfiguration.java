package lk.ijse.hostal.util;


import lk.ijse.hostal.entity.LoginDetails;
import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(LoginDetails.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Student.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) factoryConfiguration = new FactoryConfiguration();
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }


}
