package sample.controllerFiles.AdminDashBoard.ProjectTab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.model.Datasource;
import java.util.List;

public class AddProjectButtonController {

    Datasource obj;
    @FXML
    private ComboBox empCombo;
    @FXML
    private ListView employeeListView;
    @FXML
    private TextField TFpName;
    @FXML
    private TextArea TFpDesc;
    @FXML
    private DatePicker DPpStartDate;
    @FXML
    private DatePicker DPpDeadline;

    @FXML
    public void initialize()
    {
        obj=new Datasource();
        TFpName.clear();
        TFpDesc.clear();
        DPpStartDate.setValue(null);
        DPpDeadline.setValue(null);
        employeeListView.getItems().clear();
        empCombo.setValue(null);

        List<Integer> list=obj.empListCombo();
        ObservableList<Integer> observableList=FXCollections.observableArrayList(list);
        empCombo.setItems(observableList);

        String adminName=obj.idToName(1);

        employeeListView.getItems().add(adminName);
    }

    public void addEmployee() {
        if (!empCombo.getSelectionModel().isEmpty()) {
            int n = (int) empCombo.getValue();
            String employeeName = obj.idToName(n);
            ObservableList<String> s=employeeListView.getItems();

            if(!s.contains(employeeName))
                employeeListView.getItems().add(employeeName);
        }
    }

    public void addProject() {
        String pName=TFpName.getText();
        String pDesc=TFpDesc.getText();
        String pStartDate="";
        String pDeadline="";

        if(pName.trim().isEmpty() || pDesc.trim().isEmpty()|| DPpStartDate.getValue()==null||DPpDeadline.getValue()==null)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in Creating Project");
            alert.setContentText("All Fields are cumpolsory");
            alert.show();
        }
        else{

            if(DPpStartDate.getValue().compareTo(DPpDeadline.getValue())>0)
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error in Dates");
                alert.setContentText("Start Date is after Deadline");
                alert.show();
            }
            else {
                pStartDate = DPpStartDate.getValue().toString();
                pDeadline = DPpDeadline.getValue().toString();
                ObservableList<String> s = employeeListView.getItems();
                obj = new Datasource();
                obj.insertProject(pName, pDesc, pStartDate, pDeadline, s);
                initialize();


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Project");
                alert.setContentText("Project Created Successfully");
                alert.show();
            }
        }
    }
}
