package lk.ijse.hostal.controller.manageStudents;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.hostal.controller.util.FormValidate;
import lk.ijse.hostal.controller.util.RegexTypes;
import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.entity.embedded.Contact;
import lk.ijse.hostal.util.TransferObjects;

import java.util.ArrayList;
import java.util.Collections;

public class ContactFormController {

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtContactNumber;

    private Stage stage;
    private ArrayList<JFXTextField> textFields = new ArrayList<>();

    public void initialize() {
        textFields.clear();
        Collections.addAll(textFields, txtDescription, txtContactNumber);
        TransferObjects.sendObject(null);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        //Validation
        boolean validate = FormValidate.validate(textFields, RegexTypes.DESCRIPTION, RegexTypes.CONTACT);
        if (!validate) return;

        String description = txtDescription.getText();
        String number = txtContactNumber.getText();

        Contact contact = new Contact(description, number);
        /*  Send object */
        TransferObjects.sendObject(contact);
        /*--------------*/

        /*  Hide window */
        stage = (Stage) txtContactNumber.getScene().getWindow();
        stage.hide();
        /*--------------*/
    }

}
