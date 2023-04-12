package lk.ijse.hostal.controller.register;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.hostal.controller.util.Navigation;
import lk.ijse.hostal.controller.util.Routes;
import lk.ijse.hostal.dto.LoginDetailsDTO;
import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.LoginDetailsBO;

import java.util.ArrayList;
import java.util.List;

public class RegisterFormController {

    public AnchorPane pane;
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtLName;

    @FXML
    private TextField txtMName;

    @FXML
    private PasswordField txtPassword1;

    @FXML
    private PasswordField txtPassword2;

    @FXML
    private TextField txtUserName;

    @FXML
    private Label lblId;

    private final LoginDetailsBO loginDetailsBO = (LoginDetailsBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.LOGIN_DETAILS);
    private List<Address> addresses = new ArrayList<>();

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws Exception {
        String id = lblId.getText();
        String fName = txtFName.getText();
        String mName = txtMName.getText();
        String lName = txtLName.getText();
        String email = txtUserName.getText();
        String pass1 = txtPassword1.getText();
        String pass2 = txtPassword2.getText();

        if (!pass1.equals(pass2)) {
            new Alert(Alert.AlertType.WARNING, "Password not matched !").show();
            return;
        }


        LoginDetailsDTO loginDetailsDTO = new LoginDetailsDTO();
//        loginDetailsBO.register();

        Navigation.navigate(Routes.REGISTER_2, pane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setWidth(380.00);
        stage.setHeight(360.00);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }
}
