package lk.ijse.hostal.controller.manageStudents;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.hostal.util.TransferObjects;

public class AddressFormController {

    @FXML
    private JFXTextField txtHouseNo;

    @FXML
    private JFXTextField txtStreetName;

    @FXML
    private JFXTextField txtTown;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String houseNo = txtHouseNo.getText();
        String streetName = txtStreetName.getText();
        String town = txtTown.getText();
        String postalCode = txtPostalCode.getText();

        String address = houseNo.concat(" ")
                .concat(streetName)
                .concat(" ")
                .concat(town)
                .concat(" ")
                .concat(postalCode);
        TransferObjects.sendObject(address);
        txtPostalCode.getScene().getWindow().hide();
    }

}
