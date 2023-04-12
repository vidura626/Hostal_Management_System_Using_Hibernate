package lk.ijse.hostal.service.custom;

import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.service.SuperBO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean registerStudent(StudentDTO student) throws Exception;
    public boolean updateStudent(StudentDTO student) throws Exception;
    public boolean deleteStudent(String id) throws Exception;
    public StudentDTO searchStudent(String id);
    public List<StudentDTO> getAllStudents();
    public String generateLastId() throws Exception;

}
