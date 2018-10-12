package sample.controllerFiles.AdminDashBoard.TaskTab;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.model.Datasource;
import sample.model.Employee;
import sample.model.Project;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddTaskButtonController
{
    Datasource obj;
    @FXML
    private ComboBox projCombo;
    @FXML
    private TextField TFpName;
    @FXML
    private TextField TFtName;
    @FXML
    private TextArea TFtDesc;
    @FXML
    private ComboBox empCombo;
    @FXML
    private TextField TFeName;
    @FXML
    private DatePicker DPtStartDate;
    @FXML
    private DatePicker DPtDeadline;
    @FXML
    private Label LBstatus;
    @FXML
    private TextArea TFcomments;
    @FXML
    private TextField TFenterCmnt;
    @FXML
    private TextField TFattach;
    @FXML
    private ListView filesListView;

    List<String> fileNames=new ArrayList<String>();
    List<String> filePaths=new ArrayList<String>();

    @FXML
    public void initialize()
    {
        obj=new Datasource();

        TFpName.clear();
        TFeName.clear();
        TFcomments.clear();
        TFenterCmnt.clear();
        TFtDesc.clear();
        TFtName.clear();
        TFattach.clear();

        projCombo.setValue(null);
        empCombo.setValue(null);
        filesListView.getItems().clear();
        DPtDeadline.setValue(null);
        DPtStartDate.setValue(null);

        List<Integer> listProj=obj.projListCombo();
        ObservableList<Integer> observableList1=FXCollections.observableArrayList(listProj);
        projCombo.setItems(observableList1);
    }

    public void comboActionProj()
    {
        if(projCombo.getValue()!=null)
        {

            obj = new Datasource();
            int n = (int) projCombo.getValue();
            Project project = obj.idToProject(n);
            TFpName.setText(project.getpName());

            List<Integer> listEmp=obj.empListComboProj(n);
            ObservableList<Integer> observableList1=FXCollections.observableArrayList(listEmp);
            empCombo.setItems(observableList1);
        }

    }

    public void comboActionEmp()
    {
        if(empCombo.getValue()!=null)
        {
            obj=new Datasource();
            int n=(int)empCombo.getValue();
            Employee emp=obj.idToEmployee(n);
            TFeName.setText(emp.geteName());
        }
    }

    public void addComment()
    {
        if(!TFenterCmnt.getText().isEmpty())
        {
            obj=new Datasource();
            TFcomments.appendText(obj.idToName(1)+": ");
            TFcomments.appendText(TFenterCmnt.getText()+"\n");
            TFenterCmnt.clear();
        }
    }

    public void fileBrowse()
    {
        FileChooser fileChooser = new FileChooser();
        File file=fileChooser.showOpenDialog(TFcomments.getScene().getWindow());

        if(file!=null)
        {
            filesListView.getItems().add(file.getName());
            TFattach.setText("File attached: " + file.getName());

            fileNames.add(file.getName());
            filePaths.add(file.getPath());
        }
    }

    public void createTask()
    {
        if(projCombo.getValue()!=null)
        {
            obj = new Datasource();
            String pName = TFpName.getText();
            String tName = TFtName.getText();
            String eName = TFeName.getText();
            String tDesc = TFtDesc.getText();
            String tStartDate = String.valueOf(DPtStartDate.getValue());
            String tDeadline = String.valueOf(DPtDeadline.getValue());
            String tComments = TFcomments.getText();
            String status = "Open";
            Project proj = obj.idToProject((Integer) projCombo.getValue());
            LocalDate projStart = LocalDate.parse(proj.getpStartDate());
            LocalDate projDead = LocalDate.parse(proj.getpDeadline());

            if (pName.trim().isEmpty() || eName.trim().isEmpty() || tName.trim().isEmpty() || tDesc.trim().isEmpty() || tStartDate.trim().isEmpty() || tDeadline.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error in Creating Task");
                alert.setContentText("All Fields are cumpolsory");
                alert.show();
            } else {
                if (DPtStartDate.getValue().compareTo(DPtDeadline.getValue()) > 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error in Dates");
                    alert.setContentText("Start Date is after Deadline");
                    alert.show();
                } else if ((projStart.compareTo(DPtStartDate.getValue()) > 0) || (DPtDeadline.getValue().compareTo(projDead) > 0)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error in Dates");
                    alert.setContentText("Dates should be between " + projStart + " and " + projDead);
                    alert.show();
                } else {
                    int pId = (int) projCombo.getValue();
                    int eId = (int) empCombo.getValue();

                    obj.insertTask(tName, pId, eId, tDesc, tStartDate, tDeadline, tComments, status, fileNames, filePaths);
                    initialize();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add Task");
                    alert.setContentText("Task Created Successfully");
                    alert.show();
                }
            }
        }

    }

}
