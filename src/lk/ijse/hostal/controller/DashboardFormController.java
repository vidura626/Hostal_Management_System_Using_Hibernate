package lk.ijse.hostal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostal.controller.util.Navigation;
import lk.ijse.hostal.controller.util.Routes;

public class DashboardFormController {

    public AnchorPane pane;
    @FXML
    private Label lblAdminName;

    @FXML
    private AnchorPane paneContainer;

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws Exception {
        Navigation.navigate(Routes.DASHBOARD,pane);
    }

    @FXML
    void btnLogOnAction(ActionEvent event) throws Exception {
        Navigation.navigate(Routes.LOGIN,pane);
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

}
