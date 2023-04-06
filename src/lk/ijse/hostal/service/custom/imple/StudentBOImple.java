package lk.ijse.hostal.service.custom.imple;

import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.repository.RepoFactory;
import lk.ijse.hostal.repository.custom.StudentRepository;
import lk.ijse.hostal.service.custom.StudentBO;
import lk.ijse.hostal.util.Convertor;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class StudentBOImple implements StudentBO {
    private Session session = FactoryConfiguration.getInstance().getSession();
    private StudentRepository repo = (StudentRepository) RepoFactory.getInstance().getRepository(RepoFactory.Repo.STUDENT);

    @Override
    public boolean registerStudent(StudentDTO student) {
        return repo.add(Convertor.toStudent(student), session);
    }

    @Override
    public boolean updateStudent(StudentDTO student) {
        return repo.update(Convertor.toStudent(student), session);
    }

    @Override
    public StudentDTO searchStudent(String id) {
        return Convertor.fromStudent(repo.search(id, session));
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return repo.getAll(session).stream().map(Convertor::fromStudent).collect(Collectors.toList());
    }
}
