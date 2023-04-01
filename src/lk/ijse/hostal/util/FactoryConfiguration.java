package lk.ijse.hostal.util;

import lk.ijse.hostal.testEntity.Addres;
import lk.ijse.hostal.testEntity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Addres.class);
//                .addAnnotatedClass(Student.class)
//                .addAnnotatedClass(Room.class)
//                .addAnnotatedClass(Reservation.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        if(factoryConfiguration==null)factoryConfiguration=new FactoryConfiguration();
        return factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }


}
