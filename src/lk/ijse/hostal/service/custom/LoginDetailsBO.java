package lk.ijse.hostal.service.custom;

import lk.ijse.hostal.dto.LoginDetailsDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.service.SuperBO;

import java.util.List;

public interface LoginDetailsBO extends SuperBO {
    LoginDetailsDTO search(String username) throws Exception;
    boolean check(String username, String password);
    boolean register(LoginDetailsDTO loginDetailsDTO) throws Exception;
    boolean update(LoginDetailsDTO loginDetailsDTO) throws Exception;
    boolean delete(String username) throws Exception;
    List<LoginDetailsDTO> getAll();
}
