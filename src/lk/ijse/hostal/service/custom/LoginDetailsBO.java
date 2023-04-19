package lk.ijse.hostal.service.custom;

import lk.ijse.hostal.dto.LoginDetailsDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.service.SuperBO;

import java.util.List;

public interface LoginDetailsBO extends SuperBO {
    LoginDetailsDTO search(int id) throws Exception;
    boolean check(int id, String password) throws Exception;
    boolean register(LoginDetailsDTO loginDetailsDTO) throws Exception;
    boolean update(LoginDetailsDTO loginDetailsDTO) throws Exception;
    boolean delete(int id) throws Exception;
    int getLastId() throws Exception;
    List<LoginDetailsDTO> getAll();
}
