package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Datasource;

public class Main extends Application {

    Datasource obj=new Datasource();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/LoginPage.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/AdminDashBoard/AdminDashBoard.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/EmployeeDashBoard/EmployeeDashBoard.fxml"));

        primaryStage.setTitle("Follow-Up Tracker");
        primaryStage.setScene(new Scene(root, 1350, 680));
        primaryStage.setResizable(false);
        primaryStage.show();

        setUp();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setUp()
    {
        obj.open();
        obj.createProject();
        obj.createEmployee();
        obj.createTask();
        obj.createFile();
        obj.createProjEmp();
        obj.insertAdmin();
        obj.close();
    }
}
