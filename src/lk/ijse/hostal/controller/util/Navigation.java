package lk.ijse.hostal.controller.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    public static void navigate(Routes routes, AnchorPane pane) throws Exception {
        pane.getChildren().clear();
        Stage window = (Stage) pane.getScene().getWindow();
        String location = null;

        switch (routes) {
            case CONTACT_FORM:
                window.setTitle("Add New Contact");
                location = "/manageStudents/ContactForm.fxml";
                break;
            case ADDRESS_FORM:
                window.setTitle("Add New Address");
                location = "/manageStudents/AddressForm.fxml";
                break;
            case LOGIN:
                window.setTitle("Login");
                location = "/LoginForm.fxml";
                break;
            case ROOMS:
                window.setTitle("Manage Rooms");
                location = "/manageRooms/ManageRoomForm.fxml";
                break;
            case REGISTER_1:
                window.setTitle("Register");
                location = "/register/RegisterForm.fxml";
                break;
            case REGISTER_2:
                window.setTitle("Confirmation");
                location = "/register/ConfirmEmailForm.fxml";
                break;
            case STUDENTS_ADD:
                window.setTitle("Add New Student");
                location = "/manageStudents/AddStudentForm.fxml";
                break;
            case STUDENTS_MNG:
                window.setTitle("Manage Students");
                location = "/manageStudents/ManageStudentForm.fxml";
                break;
            case DASHBOARD:
                window.setTitle("Dashboard");
                location = "/DashboardForm.fxml";
                break;
            case RESERVATIONS:
                window.setTitle("Reservations");
                location = "/ReservationForm.fxml";
                break;
            case REPORTS:
                window.setTitle("Reports");
                location = "/reports/reportsForm.fxml";
                break;
            default:
                throw new Exception("Ui not found !");
        }
        initUI(location, pane);
    }

    private static void initUI(String location, AnchorPane pane) throws IOException {
        pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("../../view" + location)));
    }
}
