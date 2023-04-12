package lk.ijse.hostal.service.custom.imple;

import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.repository.RepoFactory;
import lk.ijse.hostal.repository.custom.StudentRepository;
import lk.ijse.hostal.service.custom.StudentBO;
import lk.ijse.hostal.util.Convertor;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class StudentBOImple implements StudentBO {
    private Session session;
    private StudentRepository repo = (StudentRepository) RepoFactory.getInstance().getRepository(RepoFactory.Repo.STUDENT);

    @Override
    public boolean registerStudent(StudentDTO student) throws Exception {
        openSession();
        repo.add(Convertor.toStudent(student), session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public boolean updateStudent(StudentDTO student) throws Exception {
        openSession();
        repo.update(Convertor.toStudent(student), session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        openSession();
        repo.delete(id, session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public StudentDTO searchStudent(String id) {
        openSession();
        StudentDTO studentDTO = Convertor.fromStudent(repo.search(id, session));
        closeAndCommitSession();
        return studentDTO;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        openSession();
        List<StudentDTO> collect = repo.getAll(session).stream().map(Convertor::fromStudent).collect(Collectors.toList());
        closeAndCommitSession();
        return collect;
    }

    @Override
    public String generateLastId() throws Exception {
        openSession();
        String id = repo.generateNextId(session);
        closeAndCommitSession();
        return id;

    }

    @Override
    public void openSession() {
        session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
    }

    @Override
    public void closeAndCommitSession() {
        session.getTransaction().commit();
        session.close();
    }
}
