package sample.controllerFiles.AdminDashBoard.ProjectTab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.model.Datasource;
import sample.model.Project;

import java.time.LocalDate;
import java.util.List;

public class ViewEditProjectButtonController
{
    Datasource obj;
    @FXML
    private ComboBox projCombo, empCombo;
    @FXML
    private TextField TFpName;
    @FXML
    private TextArea TFpDesc;
    @FXML
    private DatePicker DPpStartDate, DPpDeadline;
    @FXML
    private ListView empListView;
    @FXML
    private Label LBstatus;

    @FXML
    public void initialize()
    {
        obj=new Datasource();
        TFpName.clear();
        TFpDesc.clear();
        DPpStartDate.setValue(null);
        DPpDeadline.setValue(null);
        projCombo.setValue(null);
        empCombo.setValue(null);
        empListView.getItems().clear();
        LBstatus.setText("");


        List<Integer> listProj=obj.projListCombo();
        ObservableList<Integer> observableList1=FXCollections.observableArrayList(listProj);
        projCombo.setItems(observableList1);

        List<Integer> listEmp=obj.empListCombo();
        ObservableList<Integer> observableList2=FXCollections.observableArrayList(listEmp);
        empCombo.setItems(observableList2);

    }

    public void comboAction()
    {
        if(projCombo.getValue()!=null)
        {
            obj = new Datasource();
            int n = (int) projCombo.getValue();
            Project project = obj.idToProject(n);
            TFpName.setText(project.getpName());
            TFpDesc.setText(project.getpDesc());
            DPpStartDate.setValue(LocalDate.parse(project.getpStartDate()));
            DPpDeadline.setValue(LocalDate.parse(project.getpDeadline()));

            ObservableList<String> obs = FXCollections.observableArrayList(obj.empProj(n));
            empListView.setItems(obs);

            LBstatus.setText(obj.statusCheck(n));
        }
    }


    public void addEmployee() {
        if (!empCombo.getSelectionModel().isEmpty()&&!projCombo.getSelectionModel().isEmpty()) {
            int n = (int) empCombo.getValue();
            String employeeName = obj.idToName(n);
            ObservableList<String> s=empListView.getItems();

            if(!s.contains(employeeName))
                empListView.getItems().add(employeeName);
        }
    }

    public void removeEmp()
    {
        if(empListView.getSelectionModel().getSelectedItem()!=null) {
            String s = empListView.getSelectionModel().getSelectedItem().toString();
            ObservableList<String> obs = empListView.getItems();
            if (!s.equals(obs.get(0)))
                empListView.getItems().remove(s);
        }
    }

    public void saveChanges()
    {
        if(projCombo.getValue()!=null)
        {
            obj = new Datasource();

            if(DPpStartDate.getValue().compareTo(DPpDeadline.getValue())>0)
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error in Dates");
                alert.setContentText("Start Date is after Deadline");
                alert.show();
            }
            else {
                    int pId = (int) projCombo.getValue();
                    String pName = TFpName.getText();
                    String pDesc = TFpDesc.getText();
                    String pStartDate = String.valueOf(DPpStartDate.getValue());
                    String pDeadline = String.valueOf(DPpDeadline.getValue());
                    ObservableList<String> obs = empListView.getItems();

                if(projCombo.getValue()!=null && !pName.trim().isEmpty() && !pDesc.trim().isEmpty() &&
                        !pStartDate.trim().isEmpty() && !pDeadline.trim().isEmpty()) {
                    obj.updateProject(pId, pName, pDesc, pStartDate, pDeadline, obs);
                    initialize();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("View/Edit Project");
                    alert.setContentText("Changes Saved Successfully");
                    alert.show();
                }
                else
                {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error in Editing Project");
                    alert.setContentText("Select a Project and make sure all fields are filled");
                    alert.show();
                }
            }
        }
    }
}