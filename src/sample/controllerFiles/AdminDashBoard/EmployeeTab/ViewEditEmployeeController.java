package sample.controllerFiles.AdminDashBoard.EmployeeTab;

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
    private ComboBox empCombo;
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
        TFeName.clear();
        TFeUsername.clear();
        TFePassword.clear();
        TFeEmail.clear();
        TFePhone.clear();

        empCombo.setValue(null);

        projListView.getItems().clear();
        taskListView.getItems().clear();

        List<Integer> list=obj.empListCombo();
        ObservableList<Integer> observableList=FXCollections.observableArrayList(list);
        empCombo.setItems(observableList);
    }

    public void comboAction()
    {
        if(empCombo.getValue()!=null)
        {
            obj=new Datasource();
            int n= (int) empCombo.getValue();
            Employee emp=obj.idToEmployee(n);
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

    }

    public void saveChanges()
    {
        if(empCombo.getValue()!=null)
        {
            obj=new Datasource();
            int eId = (int) empCombo.getValue();
            String eName = TFeName.getText();
            String eUsername = TFeUsername.getText();
            String ePassword = TFePassword.getText();
            String eEmail = TFeEmail.getText();
            String ePhone = TFePhone.getText();


            if(empCombo.getValue()!=null && !eName.trim().isEmpty() && !eUsername.trim().isEmpty() &&
                    !ePassword.trim().isEmpty() && !eEmail.trim().isEmpty() && !ePhone.trim().isEmpty())
            {
                obj.updateEmployee(eId, eName, eUsername, ePassword, eEmail, ePhone);
                initialize();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("View/Edit Employee");
                alert.setContentText("Changes Saved Successfully");
                alert.show();
            }
            else
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error in Editing Employee");
                alert.setContentText("Select an Employee and make sure all fields are filled");
                alert.show();
            }
        }
    }
}
