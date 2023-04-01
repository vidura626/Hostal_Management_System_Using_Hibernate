package lk.ijse.hostal.repository.custom.imple;

import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.repository.custom.StudentRepository;
import org.hibernate.Session;

import java.util.List;

public class StudentRepositoryImple implements StudentRepository {

    @Override
    public String add(Student obj, Session session) {
        session.beginTransaction();
        try {
            String save = (String) session.save(obj);
            session.getTransaction().commit();
            return save;
        }catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public boolean update(Student obj, Session session) {
        session.beginTransaction();
        try {
            session.update(obj);
            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean delete(String id, Session session) {
        session.beginTransaction();
        try {
            Student load = session.load(Student.class, id);
            session.delete(load);
            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Student search(String id, Session session) {
        try {
            return session.get(Student.class, id);
        }catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<Student> getAll(Session session) {
        try{
            List<Student> from_student = session.createQuery("From Student").getResultList();
            return from_student;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String generateNextId() {
        return null;
    }
}
