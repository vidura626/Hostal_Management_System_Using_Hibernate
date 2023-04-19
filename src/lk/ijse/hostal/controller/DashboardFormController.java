package lk.ijse.hostal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.hostal.controller.util.Navigation;
import lk.ijse.hostal.controller.util.Routes;
import lk.ijse.hostal.util.TransferObjects;

public class DashboardFormController {

    public AnchorPane pane;
    @FXML
    private Label lblAdminName;

    @FXML
    private AnchorPane paneContainer;

    public void initialize() {
        lblAdminName.setText(String.valueOf(TransferObjects.recieveObject()));
        TransferObjects.clear();
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws Exception {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }

    @FXML
    void btnLogOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setResizable(false);
        pane.getChildren().clear();
        pane = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
        stage.setTitle("Login");
    }

    @FXML
    void btnMngRoomsOnAction(ActionEvent event) throws Exception {
        Navigation.navigate(Routes.ROOMS, paneContainer);
    }

    @FXML
    void btnMngStudentsOnAction(ActionEvent event) throws Exception {
        Navigation.navigate(Routes.STUDENTS_MNG, paneContainer);
    }

    @FXML
    void btnReservationsOnAction(ActionEvent event) throws Exception {
        Navigation.navigate(Routes.RESERVATIONS, paneContainer);
    }

    @FXML
    void btnUsersOnAction(ActionEvent event) throws Exception {
        Navigation.navigate(Routes.USERS, paneContainer);

    }

}
