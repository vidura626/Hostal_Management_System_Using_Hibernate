package lk.ijse.hostal.service.custom;

import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.service.SuperBO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean registerStudent(StudentDTO student);
    public boolean updateStudent(StudentDTO student);
    public List<StudentDTO> getAllStudents();
}
