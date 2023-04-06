package lk.ijse.hostal.controller.manageStudents;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.entity.embedded.Contact;
import lk.ijse.hostal.util.TransferObjects;

public class ContactFormController {

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtContactNumber;

    private Stage stage;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String description = txtDescription.getText();
        String number = txtContactNumber.getText();

        Contact contact = new Contact(description,number);
        /*  Send object */
        TransferObjects.sendObject(contact);
        /*--------------*/

        /*  Hide window */
        stage = (Stage) txtContactNumber.getScene().getWindow();
        stage.hide();
        /*--------------*/
    }

}
