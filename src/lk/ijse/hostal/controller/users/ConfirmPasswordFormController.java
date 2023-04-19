package lk.ijse.hostal.controller.users;

import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.LoginDetailsBO;
import lk.ijse.hostal.util.TransferObjects;

public class ConfirmPasswordFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXPasswordField txtOldPass;

    private final LoginDetailsBO loginDetailsBO = (LoginDetailsBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.LOGIN_DETAILS);

    private int id;

    public void initialize() {
        id = (int) TransferObjects.recieveObject();
        System.out.println(id);
        TransferObjects.clear();
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) throws Exception {
        boolean check = loginDetailsBO.check(id, txtOldPass.getText());
        if (check) {
            TransferObjects.sendObject(Boolean.TRUE);
        } else {
            TransferObjects.sendObject(Boolean.FALSE);
        }
        txtOldPass.getScene().getWindow().hide();
    }

}
