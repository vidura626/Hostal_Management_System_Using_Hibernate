package lk.ijse.hostal.controller.manageStudents;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostal.controller.util.Navigation;
import lk.ijse.hostal.controller.util.Routes;

public class ManageStudentFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<?> tblManageStudent;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    void btnStudentRegister(ActionEvent event) throws Exception {
        Navigation.navigate(Routes.STUDENTS_ADD, pane);
    }

}
