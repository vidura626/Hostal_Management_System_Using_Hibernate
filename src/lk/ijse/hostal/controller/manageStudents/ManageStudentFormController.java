package lk.ijse.hostal.controller.manageStudents;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostal.controller.TM.MngStudentTM;
import lk.ijse.hostal.controller.util.Navigation;
import lk.ijse.hostal.controller.util.Routes;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.dto.embedded.Name;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.StudentBO;
import lk.ijse.hostal.util.TransferObjects;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ManageStudentFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<MngStudentTM> tblManageStudent;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<MngStudentTM, Name> colName;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colMore;

    private List<Stage> stages = new ArrayList<>();
    private Stage stage = new Stage();
    private final StudentBO studentBO = (StudentBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.STUDENT);

    public void initialize() {
        setCellValueFactory();
        setAllData();
    }

    private void setAllData() {
        tblManageStudent.getItems().clear();
        List<StudentDTO> allStudents = studentBO.getAllStudents();
        for (StudentDTO st : allStudents) {
            JFXButton update = new JFXButton("Update");
            JFXButton delete = new JFXButton("Delete");
            JFXButton more = new JFXButton("more..");
            tblManageStudent.getItems().add(
                    new MngStudentTM(
                            st.getId(),
                            st.getNic(),
                            st.getName(),
                            st.getGender().toString(),
                            st.getDob(),
                            st.getJoinedDate(),
                            update,
                            delete,
                            more
                    )
            );
            update.setOnAction(event -> {
                if (tblManageStudent.getSelectionModel().isEmpty()) {
                    new Alert(Alert.AlertType.INFORMATION, "Select a row").show();
                    return;
                }
                try {
                    TransferObjects.sendObject(studentBO.searchStudent(tblManageStudent.getSelectionModel().getSelectedItem().getId()));
                    showingStage(Routes.STUDENTS_ADD, stage);
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            delete.setOnAction(event -> {
                if (tblManageStudent.getSelectionModel().isEmpty()) {
                    new Alert(Alert.AlertType.INFORMATION, "Select a row").show();
                    return;
                }
                String id = tblManageStudent.getSelectionModel().getSelectedItem().getId();
                ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                Optional<ButtonType> buttonType = new Alert(Alert.AlertType.WARNING, "Are you sure ? ", ok, cancel).showAndWait();
                if (buttonType.orElse(cancel) == ok) {
                    try {
                        boolean b = studentBO.deleteStudent(id);
                        if(b){
                            new Alert(Alert.AlertType.CONFIRMATION, "Student Removal Successfully ! ").show();
                        }else {
                            System.out.println("sfujsefwsf");
                        }
                        setAllData();
                        tblManageStudent.refresh();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            more.setOnAction(event -> {
                try {
                    if (tblManageStudent.getSelectionModel().isEmpty()) {
                        new Alert(Alert.AlertType.INFORMATION, "Select a row").show();
                        return;
                    }
                    String id = tblManageStudent.getSelectionModel().getSelectedItem().getId();
                    TransferObjects.sendObject(id);
                    showingStage(Routes.VIEW_MORE, stage);
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        tblManageStudent.refresh();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        colMore.setCellValueFactory(new PropertyValueFactory<>("more"));
    }

    @FXML
    void btnStudentRegister(ActionEvent event) throws Exception {
            showingStage(Routes.STUDENTS_ADD, stage);
            stage.close();
            setAllData();
    }

    void showingStage(Routes route, Stage stage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        stage.setScene(new Scene(anchorPane));
        Navigation.navigate(route, anchorPane);
        if (stage.isShowing()) {
            stage.hide();
        } else {
            stage.showAndWait();
        }
    }
}
