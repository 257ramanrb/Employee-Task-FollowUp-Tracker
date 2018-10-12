package sample.controllerFiles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Datasource;

import java.io.IOException;
import java.sql.*;
import java.util.regex.Pattern;

public class LoginPageController {

    @FXML
    private TextField TFusername_IN;
    @FXML
    private TextField TFempName_UP;
    @FXML
    private TextField TFusername_UP;
    @FXML
    private PasswordField TFpassword_UP;
    @FXML
    private TextField TFemail_UP;
    @FXML
    private TextField TFphone_UP;

    @FXML
    private PasswordField TFpassword_IN;
    @FXML
    private Button signIn;
    @FXML
    private Button signUp;


    Datasource obj;
    Connection conn;
    Statement stmnt;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    public void loadFxmlSignIn (ActionEvent event) throws IOException {
        try {

            String username=TFusername_IN.getText();
            String password=TFpassword_IN.getText();

            if(username.trim().isEmpty() || password.trim().isEmpty())
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error in Sign In");
                alert.setContentText("Username or Password can't be empty");
                alert.show();
            }

            else {

                try {
                    conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");

                preparedStatement=conn.prepareStatement("SELECT * FROM employee WHERE eUsername =? and ePassword=?;");
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,password);
                resultSet=preparedStatement.executeQuery();

                if(!resultSet.next())
                {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error in Sign In");
                    alert.setContentText("Username/Password Incorrect");
                    alert.show();
                    return;
                }

                    if(resultSet.getInt(1)==1) {
                        Parent root1 = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AdminDashBoard/AdminDashBoard.fxml"));

                        Stage stageLogin = (Stage) signIn.getScene().getWindow();

                        Stage stage = new Stage();
                        stage.setTitle("Follow-Up Tracker");
                        stage.setScene(new Scene(root1, 1350, 680));
                        stage.setResizable(false);
                        stage.show();

                        stageLogin.close();

                    }

                    else{


                        stmnt=conn.createStatement();
                        int n =resultSet.getInt(1);
                        stmnt.execute("DELETE FROM name;");
                        stmnt.execute("INSERT INTO name VALUES("+n+");");
                        Parent root1 = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EmployeeDashBoard/EmployeeDashBoard.fxml"));

                        Stage stageLogin=(Stage)signUp.getScene().getWindow();

                        Stage stage=new Stage();
                        stage.setTitle("Follow-Up Tracker");
                        stage.setScene(new Scene(root1, 1350, 680));
                        stage.setResizable(false);
                        stage.show();

                        stageLogin.close();

                    }

                        preparedStatement.close();
                        resultSet.close();
                        conn.close();

                }catch (SQLException e)
                {
                    e.printStackTrace();
                }

            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }


    public void loadFxmlEmployeeDashBoard (ActionEvent event) throws IOException {

        String empName=TFempName_UP.getText();
        String eUsername=TFusername_UP.getText();
        String ePassword=TFpassword_UP.getText();
        String eEmail=TFemail_UP.getText();
        String ePhone=TFphone_UP.getText();

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        if(empName.trim().isEmpty() || eUsername.trim().isEmpty()||ePassword.trim().isEmpty()||eEmail.trim().isEmpty()||ePhone.trim().isEmpty())
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in Sign Up");
            alert.setContentText("All Fields are cumpolsory");
            alert.show();
        }
        else if(!pat.matcher(eEmail).matches())
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in Sign Up");
            alert.setContentText("Please Enter Valid Email");
            alert.show();
        }

        else if(!((ePhone.charAt(0)=='9'||ePhone.charAt(0)=='8'||ePhone.charAt(0)=='7') && ePhone.length()==10 && ePhone.matches("[0-9]+")))
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in Sign Up");
            alert.setContentText("Please Enter Valid Mobile Number");
            alert.show();
        }

        else {
            try {

                obj=new Datasource();
                obj.insertEmployee( empName, eUsername, ePassword, eEmail, ePhone);

                conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
                preparedStatement=conn.prepareStatement("SELECT * FROM employee WHERE eUsername =? and ePassword=?;");
                preparedStatement.setString(1,eUsername);
                preparedStatement.setString(2,ePassword);
                resultSet=preparedStatement.executeQuery();

                stmnt=conn.createStatement();
                int n =resultSet.getInt(1);
                stmnt.execute("DELETE FROM name;");
                stmnt.execute("INSERT INTO name VALUES("+n+");");

                Parent root1 = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EmployeeDashBoard/EmployeeDashBoard.fxml"));

                Stage stageLogin = (Stage) signUp.getScene().getWindow();

                Stage stage = new Stage();
                stage.setTitle("Follow-Up Tracker");
                stage.setScene(new Scene(root1, 1350, 680));
                stage.setResizable(false);
                stage.show();

                stageLogin.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
