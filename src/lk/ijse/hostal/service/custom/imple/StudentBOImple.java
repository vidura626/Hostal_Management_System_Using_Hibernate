package lk.ijse.hostal.service.custom.imple;

import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.repository.RepoFactory;
import lk.ijse.hostal.repository.custom.StudentRepository;
import lk.ijse.hostal.service.custom.StudentBO;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.List;

public class StudentBOImple implements StudentBO {
    private Session session = FactoryConfiguration.getInstance().getSession();
    private StudentRepository repo = (StudentRepository) RepoFactory.getInstance().getRepository(RepoFactory.Repo.STUDENT);

    @Override
    public boolean registerStudent(StudentDTO student) {
        return false;
    }

    @Override
    public boolean updateStudent(StudentDTO student) {
        return false;
    }

    @Override
    public StudentDTO searchStudent(String id) {
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return null;
    }
}
