package lk.ijse.hostal.service.custom;

import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.service.SuperBO;

import java.util.List;

public interface RoomBO extends SuperBO {
    public boolean addRoom(RoomDTO roomDto);
    public boolean updateRoom(RoomDTO roomDto);
    public RoomDTO searchRoom(String id);
    public List<RoomDTO> getAllRooms();
}
