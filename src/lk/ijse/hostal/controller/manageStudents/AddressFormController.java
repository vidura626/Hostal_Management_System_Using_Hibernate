package lk.ijse.hostal.controller.manageStudents;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.util.TransferObjects;

public class    AddressFormController {

    @FXML
    private JFXTextField txtHouseNo;

    @FXML
    private JFXTextField txtStreetName;

    @FXML
    private JFXTextField txtTown;

    @FXML
    private JFXTextField txtPostalCode;

    private Stage stage;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String houseNo = txtHouseNo.getText();
        String streetName = txtStreetName.getText();
        String town = txtTown.getText();
        String postalCode = txtPostalCode.getText();

        Address address = new Address(houseNo, streetName, town, postalCode);
        /*  Send object */
        TransferObjects.sendObject(address);
        /*--------------*/

        /*  Hide window */
        stage = (Stage) txtPostalCode.getScene().getWindow();
        stage.hide();
        /*--------------*/
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }
}
