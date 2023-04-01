package lk.ijse.hostal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.hostal.entity.LoginDetails;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.LoginDetailsBO;

public class LoginFormController {
    LoginDetailsBO loginDetailsBO = (LoginDetailsBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.LOGIN_DETAILS);
    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        boolean isTrue = loginDetailsBO.search(txtUsername.getText(), txtPassword.getText());
        if (isTrue) {
            //navigation to Dashboard
            return;
        }
        new Alert(Alert.AlertType.WARNING,"Username or password is not matched !").show();
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {

    }

}
