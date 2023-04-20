package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.hostal.controller.util.Navigation;
import lk.ijse.hostal.controller.util.Routes;
import lk.ijse.hostal.dto.LoginDetailsDTO;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.LoginDetailsBO;
import lk.ijse.hostal.service.custom.ReservationBO;
import lk.ijse.hostal.service.custom.RoomBO;
import lk.ijse.hostal.service.custom.StudentBO;
import lk.ijse.hostal.util.TransferObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class LoginFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    LoginDetailsBO loginDetailsBO = (LoginDetailsBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.LOGIN_DETAILS);
    StudentBO studentBO = (StudentBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.STUDENT);
    RoomBO roomBO = (RoomBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.ROOM);
    ReservationBO reservationBO = (ReservationBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.RESERVATION);

    public void initialize() {
        /*  Loan all data for caches    */
        loginDetailsBO.getAll();
        studentBO.getAllStudents();
        roomBO.getAllRooms();
        reservationBO.getAllReservations();
        /*------------------------------*/
        try {
            loginDetailsBO.register(new LoginDetailsDTO(1, "User", "user", "user@gmail.com", "1234"));
        } catch (Exception e) {
        }
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws Exception {
        String username = txtUsername.getText();
        String pass = txtPassword.getText();
        List<LoginDetailsDTO> all = loginDetailsBO.getAll();
        for (LoginDetailsDTO detailsDTO : all) {
            if (detailsDTO.getUsername().equals(username)) {
                if (detailsDTO.getPassword().equals(pass)) {
                    TransferObjects.sendObject(detailsDTO.getName());
                    Stage window = (Stage) txtUsername.getScene().getWindow();
                    window.setTitle("Dashboard");
                    window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
                    return;
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Username or password is not matched !").show();
                }
            }
        }
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {

    }

    @FXML
    void showPasswordOnMousePressed(MouseEvent event) {

    }

}
