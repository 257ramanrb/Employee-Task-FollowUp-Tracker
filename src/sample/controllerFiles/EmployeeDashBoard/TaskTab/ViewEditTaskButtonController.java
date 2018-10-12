package sample.controllerFiles.EmployeeDashBoard.TaskTab;

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
import sample.model.Task;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewEditTaskButtonController extends Application
{
    Datasource obj;

    @FXML
    private ComboBox projCombo;
    @FXML
    private TextField TFpName;
    @FXML
    private ComboBox taskCombo;
    @FXML
    private TextField TFtName;
    @FXML
    private TextArea TFtDesc;
    @FXML
    private ComboBox empCombo;
    @FXML
    private TextField TFeName;
    @FXML
    private TextField TFtStartDate;
    @FXML
    private TextField TFtDeadline;
    @FXML
    private ComboBox statusCombo;
    @FXML
    private TextArea TFcomments;
    @FXML
    private TextField TFenterCmnt;
    @FXML
    private ListView filesListView;
    @FXML
    private TextField TFattach;

    int empId;

    List<String> fileNames=new ArrayList<String>();
    List<String> filePaths=new ArrayList<String>();
    @FXML
    public void initialize()
    {
        obj=new Datasource();

        projCombo.setValue(null);
        setUp();
        List<Integer> listProj=obj.projEmpListCombo();
        ObservableList<Integer> observableList1=FXCollections.observableArrayList(listProj);
        projCombo.setItems(observableList1);
    }

    public void setUp()
    {
        TFpName.clear();
        TFeName.clear();
        TFcomments.clear();
        TFenterCmnt.clear();
        TFtDesc.clear();
        TFtName.clear();
        TFattach.clear();
        fileNames.clear();
        filePaths.clear();


        empCombo.setValue(null);
        statusCombo.setValue(null);
        taskCombo.setValue(null);
        filesListView.getItems().clear();
        TFtStartDate.clear();
        TFtDeadline.clear();
    }

    public void comboActionProj()
    {
        if(projCombo.getValue()!=null)
        {
            setUp();
            obj = new Datasource();
            int n = (int) projCombo.getValue();
            Project project = obj.idToProject(n);
            TFpName.setText(project.getpName());

            List<Integer> listEmp=obj.empListComboProj(n);
            ObservableList<Integer> observableList1=FXCollections.observableArrayList(listEmp);
            empCombo.setItems(observableList1);

            List<Integer> listTask=obj.taskListComboProjEmp(n, obj.nameReturn());
            ObservableList<Integer> observableList2=FXCollections.observableArrayList(listTask);
            taskCombo.setItems(observableList2);
        }
    }

    public void comboActionTask()
    {
        if(taskCombo.getValue()!=null)
        {
            obj=new Datasource();
            int n=(int)taskCombo.getValue();
            Task task=obj.idToTask(n);
            empId=task.gettEId();
            TFtName.setText(task.gettName());
            TFtDesc.setText(task.gettDesc());
            TFcomments.setText(task.gettComment());
            TFtStartDate.setText(task.gettStartDate());
            TFtDeadline.setText(task.gettDeadline());
            statusCombo.setValue(task.gettStatus());
            empCombo.setValue(task.gettEId());
            int e=(int)empCombo.getValue();
            Employee emp = obj.idToEmployee(e);
            TFeName.setText(emp.geteName());

            ObservableList<String> obs = FXCollections.observableArrayList(obj.files(n));
            filesListView.setItems(obs);
        }
    }

    public void comboActionEmp()
    {
        if(empCombo.getValue()!=null)
        {
            obj=new Datasource();
            int n = (int) empCombo.getValue();
            Employee emp = obj.idToEmployee(n);
            TFeName.setText(emp.geteName());
        }
    }


    public void addComment()
    {
        if(!TFenterCmnt.getText().isEmpty())
        {
            obj=new Datasource();
            TFcomments.appendText(obj.idToName(obj.nameReturn())+": ");
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

    public void openFile()
    {
        obj=new Datasource();
        if(filesListView.getSelectionModel().getSelectedItem()!=null) {
            String s = filesListView.getSelectionModel().getSelectedItem().toString();
            String path=obj.nameToPath(s);

            HostServices hostServices = getHostServices();
            hostServices.showDocument(path);
        }
    }

    public void saveChanges()
    {
        if(projCombo.getValue()!=null) {
            String tName = TFtName.getText();
            String tDesc = TFtDesc.getText();
            String eName = TFeName.getText();
            String tStartDate = TFtStartDate.getText();
            String tDeadline = TFtDeadline.getText();
            String tComment = TFcomments.getText();
            String tStatus = String.valueOf(statusCombo.getValue());

            if (projCombo.getValue() != null && taskCombo.getValue() != null && empCombo.getValue() != null &&
                    statusCombo.getValue() != null && !tName.trim().isEmpty() && !tDesc.trim().isEmpty() &&
                    !eName.trim().isEmpty() && !tStartDate.trim().isEmpty() && !tDeadline.trim().isEmpty()) {
                obj = new Datasource();

                int tId = (int) taskCombo.getValue();
                int tEId = (int) empCombo.getValue();
                obj.updateTask(tId, tName, tEId, tDesc, tStartDate, tDeadline, tComment, tStatus, fileNames, filePaths);
                initialize();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("View/Edit Task");
                alert.setContentText("Changes Saved Successfully");
                alert.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error in Edititng Task");
                alert.setContentText("Select a Task and make sure all fields are filled");
                alert.show();
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

}
