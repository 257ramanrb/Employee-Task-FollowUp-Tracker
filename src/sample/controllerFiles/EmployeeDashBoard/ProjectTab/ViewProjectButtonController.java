package sample.controllerFiles.EmployeeDashBoard.ProjectTab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.model.Datasource;
import sample.model.Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewProjectButtonController
{
    Datasource obj;
    @FXML
    private ComboBox projCombo;
    @FXML
    private TextField TFpName;
    @FXML
    private TextArea TFpDesc;
    @FXML
    private TextField TFpStartDate, TFpDeadline;
    @FXML
    private ListView empListView;

    @FXML
    public void initialize()
    {
        obj=new Datasource();
        TFpName.clear();
        TFpDesc.clear();
        TFpStartDate.clear();
        TFpDeadline.clear();
        projCombo.setValue(null);
        empListView.getItems().clear();

        List<Integer> listProj=obj.projEmpListCombo();
        ObservableList<Integer> observableList1=FXCollections.observableArrayList(listProj);
        projCombo.setItems(observableList1);
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
            TFpStartDate.setText(project.getpStartDate());
            TFpDeadline.setText(project.getpDeadline());

            ObservableList<String> obs = FXCollections.observableArrayList(obj.empProj(n));

            empListView.setItems(obs);

        }
    }


}
