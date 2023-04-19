package lk.ijse.hostal.controller.manageStudents;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lk.ijse.hostal.controller.util.FormValidate;
import lk.ijse.hostal.controller.util.RegexTypes;
import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.util.TransferObjects;

import java.util.ArrayList;
import java.util.Collections;

public class AddressFormController {

    @FXML
    private JFXTextField txtHouseNo;

    @FXML
    private JFXTextField txtStreetName;

    @FXML
    private JFXTextField txtTown;

    @FXML
    private JFXTextField txtPostalCode;

    private ArrayList<JFXTextField> textFields = new ArrayList<>();
    private Stage stage;

    public void initialize() {
        textFields.clear();
        Collections.addAll(textFields, txtHouseNo, txtStreetName, txtTown, txtPostalCode);
        TransferObjects.sendObject(null);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        //Validation
        boolean validate = FormValidate.validate(textFields, RegexTypes.HOUSE_NO, RegexTypes.STREET_NAME, RegexTypes.TOWN_NAME, RegexTypes.POSTAL_CODE);
        if (!validate) return;
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
