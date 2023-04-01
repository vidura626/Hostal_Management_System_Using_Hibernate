package lk.ijse.hostal.service.custom;

import lk.ijse.hostal.dto.LoginDetailsDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.service.SuperBO;

import java.util.List;

public interface LoginDetailsBO extends SuperBO {
    LoginDetailsDTO search(String username, String password);
    boolean check(String username, String password);
    boolean register(LoginDetailsDTO loginDetailsDTO);
    boolean update(LoginDetailsDTO loginDetailsDTO);
    List<LoginDetailsDTO> getAll();
}
