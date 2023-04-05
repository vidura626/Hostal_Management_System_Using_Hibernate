package lk.ijse.hostal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostal.controller.util.Navigation;
import lk.ijse.hostal.controller.util.Routes;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.LoginDetailsBO;

import java.io.IOException;

public class LoginFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    LoginDetailsBO loginDetailsBO = (LoginDetailsBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.LOGIN_DETAILS);

    public void initialize() throws IOException {

    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws Exception {

        Stage window = (Stage) txtUsername.getScene().getWindow();
        window.setTitle("Dashboard");
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws Exception {
        Navigation.navigate(Routes.REGISTER_1, pane);
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {

    }

}
