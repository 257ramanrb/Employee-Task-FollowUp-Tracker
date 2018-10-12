package sample.controllerFiles.EmployeeDashBoard.EmployeeTab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.model.Datasource;
import sample.model.Employee;

import java.util.List;

public class ViewEditEmployeeController
{
    Datasource obj;
    @FXML
    private TextField TFeId;
    @FXML
    private TextField TFeName;
    @FXML
    private TextField TFeUsername;
    @FXML
    private TextField TFePassword;
    @FXML
    private TextField TFeEmail;
    @FXML
    private TextField TFePhone;
    @FXML
    private ListView projListView;
    @FXML
    private ListView taskListView;

    @FXML
    public void initialize()
    {
        obj=new Datasource();

        int n = obj.nameReturn();
        Employee emp=obj.idToEmployee(n);
        TFeId.setText(String.valueOf(emp.geteId()));
        TFeName.setText(emp.geteName());
        TFeUsername.setText(emp.geteUsername());
        TFePassword.setText(emp.getePassword());
        TFeEmail.setText(emp.geteEmail());
        TFePhone.setText(emp.getePhone());

        ObservableList<String> obs=FXCollections.observableArrayList(obj.projOfEmp(n));
        projListView.setItems(obs);

        ObservableList<String> obs2=FXCollections.observableArrayList(obj.taskOfEmp(n));
        taskListView.setItems(obs2);

    }

    public void saveChanges()
    {
            obj=new Datasource();
            int n = obj.nameReturn();
            String eName = TFeName.getText();
            String eUsername = TFeUsername.getText();
            String ePassword = TFePassword.getText();
            String eEmail = TFeEmail.getText();
            String ePhone = TFePhone.getText();

             if(TFeId.getText()!=null && !eName.trim().isEmpty() && !eUsername.trim().isEmpty() &&
                !ePassword.trim().isEmpty() && !eEmail.trim().isEmpty() && !ePhone.trim().isEmpty())
             {
                 obj.updateEmployee(n, eName, eUsername, ePassword, eEmail, ePhone);
                 initialize();


                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Profile");
                 alert.setContentText("Changes Saved Successfully");
                 alert.show();
             }
             else
             {
                 Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Error in Editing Profile");
                 alert.setContentText("Make sure all fields are filled");
                 alert.show();
             }

    }
}
