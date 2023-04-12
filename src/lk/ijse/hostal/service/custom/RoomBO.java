package lk.ijse.hostal.service.custom;

import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.service.SuperBO;

import java.util.List;

public interface RoomBO extends SuperBO {
    public boolean addRoom(RoomDTO roomDto) throws Exception;
    public boolean updateRoom(RoomDTO roomDto) throws Exception;
    public boolean deleteRoom(String roomId) throws Exception;
    public RoomDTO searchRoom(String id);
    public List<RoomDTO> getAllRooms();
    public String generateNextId() throws Exception;
}
