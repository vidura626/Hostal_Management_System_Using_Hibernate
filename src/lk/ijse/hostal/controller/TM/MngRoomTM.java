package lk.ijse.hostal.controller.TM;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MngRoomTM {
    private String id;
    private String type;
    private String price;
    private String qty;
    private JFXButton update;
    private JFXButton delete;
}
