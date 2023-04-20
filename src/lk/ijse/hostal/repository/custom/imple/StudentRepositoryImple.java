package lk.ijse.hostal.repository.custom.imple;

import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.repository.custom.StudentRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRepositoryImple implements StudentRepository {

    @Override
    public String add(Student obj, Session session) throws Exception {
        return (String) session.save(obj);
    }

    @Override
    public void update(Student obj, Session session) throws Exception {
        session.update(obj);
    }

    @Override
    public void delete(String id, Session session) throws Exception {
        Student get = session.load(Student.class, id);
        session.delete(get);
    }

    @Override
    public Student search(String id, Session session) {
        return session.get(Student.class, id);
    }

    @Override
    public List<Student> getAll(Session session) {
        try {
            Query from_student = session.createQuery("From Student");
            from_student.setCacheable(true);
            return from_student.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String generateNextId(Session session) {
        List resultList = session.createQuery("SELECT id FROM Student ORDER BY id DESC").setMaxResults(1).getResultList();
        if (resultList.size() > 0) {
            String id = (String) resultList.get(0);
            int newId = Integer.parseInt(id.replace("S", "")) + 1;
            return String.format("S%05d", newId);
        } else {
            return "S00001";
        }
    }
}
