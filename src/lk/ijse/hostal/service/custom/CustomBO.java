package lk.ijse.hostal.service.custom;

import lk.ijse.hostal.dto.CustomDTO;
import lk.ijse.hostal.service.SuperBO;

import java.util.List;

public interface CustomBO extends SuperBO {
    public List<CustomDTO> getList();
}
