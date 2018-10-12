package sample.model;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Datasource
{
    private Connection conn;
    private Statement stmnt, stmnt2, stmnt3;
    private ResultSet resultSet, resultSet2;
    private PreparedStatement preparedStatement;
    //*********************************************************************************************************//

    public void open()
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
        }catch(SQLException e)
        {
            System.out.println("Connection can't be established"+e.getMessage());
        }
    }

    public void close()
    {
        try
        {
            if(conn!=null)
                conn.close();
        }catch (SQLException e)
        {
            System.out.println("Connection can't be closed"+e.getMessage());
        }
    }

    public void createProject()
    {
        try{
            stmnt=conn.createStatement();
            stmnt.execute("CREATE TABLE IF NOT EXISTS project ( pId INTEGER PRIMARY KEY, pName TEXT, pDesc TEXT, pStartDate TEXT, pDeadline TEXT);");
            stmnt.close();
        }catch (SQLException e)
        {
            System.out.println("createProject() error: "+e.getMessage());
        }
    }


    public void createEmployee()
    {
        try{
            stmnt=conn.createStatement();
            stmnt.execute("CREATE TABLE IF NOT EXISTS employee ( eId INTEGER PRIMARY KEY, eName TEXT, eUsername TEXT, ePassword TEXT, eEmail TEXT, ePhone Text);");
            stmnt.close();
        }catch (SQLException e)
        {
            System.out.println("createEmployee() error: "+e.getMessage());
        }

    }


    public void createTask()
    {
        try{
            stmnt=conn.createStatement();
            stmnt.execute("CREATE TABLE IF NOT EXISTS task ( tId INTEGER PRIMARY KEY, tName TEXT, tPId INTEGER, tEId INTEGER, tDesc TEXT, tStartDate TEXT, tDeadline TEXT, tComment TEXT, tStatus TEXT);");
            stmnt.close();
        }catch (SQLException e)
        {
            System.out.println("createTask() error: "+e.getMessage());
        }
    }


    public void createFile()
    {
        try{
            stmnt=conn.createStatement();
            stmnt.execute("CREATE TABLE IF NOT EXISTS file ( fName TEXT, fPath TEXT, fTId INTEGER);");
            stmnt.close();
        }catch (SQLException e)
        {
            System.out.println("createFile() error: "+e.getMessage());
        }
    }


    public void createProjEmp()
    {
        try{
            stmnt=conn.createStatement();
            stmnt.execute("CREATE TABLE IF NOT EXISTS projEmp ( pe_pId INTEGER, pe_eId INTEGER);");
            stmnt.close();
        }catch (SQLException e)
        {
            System.out.println("createProjEmp() error: "+e.getMessage());
        }
    }

    public void insertAdmin()
    {
        try
        {
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT count(*) from employee;");

            int n=resultSet.getInt(1);
            if(n==0)
            {
                stmnt.execute("INSERT INTO employee( eName, eUsername, ePassword, eEmail, ePhone )"+
                                "Values ( 'Raman Bhadauria', 'Admin', 'Admin', '257ramanrb@gmail.com', '7291023707');");
            }
        }catch (SQLException e)
        {
            System.out.println("insertAdmin() error: "+e.getMessage());
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("insertAdminError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void insertEmployee( String empName, String eUsername, String ePassword, String eEmail, String ePhone)
    {
        try{

            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");

            preparedStatement=conn.prepareStatement("INSERT INTO employee( eName, eUsername, ePassword, eEmail, ePhone )"+
                                                        "VALUES (?,?,?,?,?);");
            preparedStatement.setString(1, empName);
            preparedStatement.setString(2, eUsername);
            preparedStatement.setString(3, ePassword);
            preparedStatement.setString(4, eEmail);
            preparedStatement.setString(5, ePhone);

            preparedStatement.execute();

        }catch (SQLException e)
        {
            System.out.println("insertEmployee() error: "+ e.getMessage());
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("insertEmployeeError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public List<Integer> empListCombo()
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT * FROM employee");

            List<Integer> empList=new ArrayList<>();
            while(resultSet.next())
            {
                Employee emp=new Employee();
                emp.seteId(resultSet.getInt("eId"));
                emp.seteName(resultSet.getString("eName"));
                emp.seteUsername(resultSet.getString("eUsername"));
                emp.setePassword(resultSet.getString("ePassword"));
                emp.seteEmail(resultSet.getString("eEmail"));
                emp.setePhone(resultSet.getString("ePhone"));
                empList.add(emp.geteId());
            }
            return empList;

        }catch (SQLException e)
        {
            System.out.println("empListCombo() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("empListComboError(): "+e.getMessage());
                e.printStackTrace();
            }
        }

    }


    public List<Integer> projListCombo()
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT * FROM project");

            List<Integer> projList=new ArrayList<>();
            while(resultSet.next())
            {
                Project proj=new Project();
                proj.setpId(resultSet.getInt("pId"));
                proj.setpName(resultSet.getString("pName"));
                proj.setpDesc(resultSet.getString("pDesc"));
                proj.setpStartDate(resultSet.getString("pStartDate"));
                proj.setpDeadline(resultSet.getString("pDeadline"));
                projList.add(proj.getpId());
            }
            return projList;

        }catch (SQLException e)
        {
            System.out.println("projListCombo() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("projListComboError(): "+e.getMessage());
                e.printStackTrace();
            }
        }

    }


    public String idToName(int id)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT eName FROM employee WHERE eId="+id);
            return resultSet.getString(1);

        }catch (SQLException e)
        {
            System.out.println("idToName() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("idToNameError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void insertProject(String pName, String pDesc, String pStartDate, String pDeadline, ObservableList list)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            stmnt.execute("INSERT INTO project( pName, pDesc, pStartDate, pDeadline)"+
                               "Values ('"+pName+"','"+pDesc+"','"+pStartDate+"','"+pDeadline+"');");

            resultSet=stmnt.executeQuery("SELECT count(*) FROM project");
            int n=resultSet.getInt(1);

            for(int i=0;i<list.size();i++)
            {
                resultSet=stmnt.executeQuery("SELECT eId FROM employee WHERE eName='"+list.get(i).toString()+"';");
                int x=resultSet.getInt(1);
                stmnt.execute("INSERT INTO projEmp VALUES("+n+","+x+");");
            }


        }catch (SQLException e)
        {
            System.out.println("addProject() error: "+e.getMessage());
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("insertProjectError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public Project idToProject(int id)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT * FROM project WHERE pId="+id+";");
            Project obj =new Project();
            obj.setpId(resultSet.getInt("pId"));
            obj.setpName(resultSet.getString("pName"));
            obj.setpDesc(resultSet.getString("pDesc"));
            obj.setpStartDate(resultSet.getString("pStartDate"));
            obj.setpDeadline(resultSet.getString("pDeadline"));
            return obj;
        }catch(SQLException e)
        {
            System.out.println("idToproject() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("idToProjectError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> empProj(int n)
    {
        try
        {
            ArrayList<String> list=new ArrayList<>();
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            Statement stmnt2=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT pe_eId FROM projEmp WHERE pe_pId="+n+";");
            ResultSet resultSet2;
            while (resultSet.next())
            {
                resultSet2= stmnt2.executeQuery("SELECT eName FROM employee WHERE eId="+resultSet.getInt(1)+";");
                String s=resultSet2.getString(1);
                list.add(s);
            }
           // System.out.println(list);
            return list;

        }catch (SQLException e)
        {
            System.out.println("empProj() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("empProjError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void updateProject(int pId, String pName, String pDesc, String pStartDate, String pDeadline, ObservableList<String> list)
    {
        try
        {
            conn=DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            stmnt.execute("UPDATE project SET pName='"+pName+"',pDesc='"+pDesc+"',pStartDate='"+pStartDate+"',pDeadline='"+pDeadline+"' WHERE pId="+pId+";");
            //Statement stmnt2=conn.createStatement();
            stmnt.execute("DELETE FROM projEmp WHERE pe_pId="+pId+";");

            for(int i=0;i<list.size();i++)
            {
                int n;
                resultSet = stmnt.executeQuery("SELECT eId FROM employee WHERE eName='" + String.valueOf(list.get(i))+"';");
                n=resultSet.getInt(1);
                stmnt.execute("INSERT INTO projEmp VALUES("+pId+","+n+");");
            }
        }catch (SQLException e)
        {
            System.out.println("updateProject() error: "+e.getStackTrace());
            e.printStackTrace();
        }  finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("updateProjectError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }


    //<******************************************************************************************************>
    //Employee Tab


    public Employee idToEmployee(int id)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT * FROM employee WHERE eId="+id+";");
            Employee obj =new Employee();
            obj.seteId(resultSet.getInt("eId"));
            obj.seteName(resultSet.getString("eName"));
            obj.seteUsername(resultSet.getString("eUsername"));
            obj.setePassword(resultSet.getString("ePassword"));
            obj.seteEmail(resultSet.getString("eEmail"));
            obj.setePhone(resultSet.getString("ePhone"));
            return obj;
        }catch(SQLException e)
        {
            System.out.println("idToEmployee() error: "+e.getMessage());
            return null;
        }  finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("idToEmployeeError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public ArrayList<String> projOfEmp(int n)
    {
        try
        {
            ArrayList<String> list=new ArrayList<>();
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            Statement stmnt2=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT pe_pId FROM projEmp WHERE pe_eId="+n+";");
            ResultSet resultSet2;
            while (resultSet.next())
            {
                resultSet2= stmnt2.executeQuery("SELECT pName FROM project WHERE pId="+resultSet.getInt(1)+";");
                String s=resultSet2.getString(1);
                list.add(s);
            }
            return list;

        }catch (SQLException e)
        {
            System.out.println("projOfEmp() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("projOfEmpError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public ArrayList<String> taskOfEmp(int n)
    {
        try
        {
            ArrayList<String> list=new ArrayList<>();
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT tName FROM task WHERE tEId="+n+";");
            while (resultSet.next())
            {
                String s=resultSet.getString(1);
                list.add(s);
            }
            return list;

        }catch (SQLException e)
        {
            System.out.println("taskOfEmp() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("taskOfEmpError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void updateEmployee(int eId,String eName,String eUsername,String ePassword,String eEmail,String ePhone)
    {
        try
        {
            conn=DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            stmnt.execute("UPDATE employee SET eName='"+eName+"',eUsername='"+eUsername+"',ePassword='"+ePassword+"',eEmail='"+eEmail+"',ePhone='"+ePhone+"' WHERE eId="+eId+";");

        }catch (SQLException e)
        {
            System.out.println("updateEmployee() error: "+e.getStackTrace());
            e.printStackTrace();
        }  finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("updateEmployeeError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    //<*********************************************************************************************>
    //Task

    public List<Integer> empListComboProj(int n)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT pe_eId FROM projEmp WHERE pe_pId="+n+";");

            List<Integer> empList=new ArrayList<>();
            while(resultSet.next())
            {
                empList.add(resultSet.getInt(1));
            }
            return empList;

        }catch (SQLException e)
        {
            System.out.println("empListCombo() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("empListComboProjError(): "+e.getMessage());
                e.printStackTrace();
            }
        }

    }

      public void insertTask(String tName,int pId,int eId,String tDesc,String tStartDate,String tDeadline,String tComments,String status,List<String> fileNames,List<String> filePaths)
      {
          try
          {
              conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
              stmnt=conn.createStatement();
              stmnt.execute("INSERT INTO task(tName, tPId, tEId, tDesc, tStartDate, tDeadline, tComment, tStatus)"+
                                "VALUES ('"+tName+"',"+pId+","+eId+",'"+tDesc+"','"+tStartDate+"','"+tDeadline+"','"+tComments+"','"+status+"');");
              resultSet=stmnt.executeQuery("SELECT COUNT(*) FROM task;");
              int n=resultSet.getInt(1);

              for(int i=0;i<fileNames.size();i++)
                stmnt.execute("INSERT INTO file VALUES('"+fileNames.get(i)+"','"+filePaths.get(i)+"',"+n+");");

          }catch (SQLException e)
          {
              System.out.println("insertTask() error: "+e.getMessage());
          }
          finally
          {
              try
              {
                  if(stmnt!=null)
                      stmnt.close();
                  if(resultSet!=null)
                      resultSet.close();
                  if(preparedStatement!=null)
                      preparedStatement.close();
                  if(conn!=null)
                      conn.close();
              }catch (SQLException e)
              {
                  System.out.println("insertTaskError(): "+e.getMessage());
                  e.printStackTrace();
              }
          }
      }


    public List<Integer> taskListComboProj(int n)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT tId FROM task WHERE tPId="+n+";");

            List<Integer> taskList=new ArrayList<>();
            while(resultSet.next())
            {
                taskList.add(resultSet.getInt(1));
            }
            return taskList;

        }catch (SQLException e)
        {
            System.out.println("taskListComboProj() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("taskListComboProjError(): "+e.getMessage());
                e.printStackTrace();
            }
        }

    }


    public Task idToTask(int id)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT * FROM task WHERE tId="+id+";");
            Task obj =new Task();
            obj.settId(resultSet.getInt("tId"));
            obj.settName(resultSet.getString("tName"));
            obj.settPId(resultSet.getInt("tPId"));
            obj.settEId(resultSet.getInt("tEId"));
            obj.settDesc(resultSet.getString("tDesc"));
            obj.settStartDate(resultSet.getString("tStartDate"));
            obj.settDeadline(resultSet.getString("tDeadline"));
            obj.settComment(resultSet.getString("tComment"));
            obj.settStatus(resultSet.getString("tStatus"));
            return obj;
        }catch(SQLException e)
        {
            System.out.println("idToTask() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("idToTaskError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public ArrayList<String> files(int n)
    {
        try
        {
            ArrayList<String> list=new ArrayList<>();
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet= stmnt.executeQuery("SELECT fName FROM file WHERE fTId="+n+";");
            while (resultSet.next())
            {
                String s=resultSet.getString(1);
                list.add(s);
            }
            return list;

        }catch (SQLException e)
        {
            System.out.println("files() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("filesError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public String nameToPath(String fName)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet= stmnt.executeQuery("SELECT fPath FROM file WHERE fName='"+fName+"';");
            String s=resultSet.getString(1);
            return s;

        }catch (SQLException e)
        {
            System.out.println("nameToPath() error: "+e.getMessage());
            return null;
        }  finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("nameToPathError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void updateTask(int tId, String tName, int tEId, String tDesc, String tStartDate, String tDeadline, String tComment, String tStatus, List<String>fileNames, List<String>filePaths)
    {
        try
        {
            conn=DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();

            stmnt.execute("UPDATE task SET tName='"+tName+"',tDesc='"+tDesc+"',tStartDate='"+tStartDate+"',tDeadline='"+tDeadline+"', tEId="+tEId+",tComment='"+tComment+"', tStatus='"+tStatus+"' WHERE tId="+tId+";");

            for(int i=0;i<fileNames.size();i++)
            {
                stmnt.execute("INSERT INTO file VALUES('"+fileNames.get(i)+"','"+filePaths.get(i)+"',"+tId+");");
            }

        }catch (SQLException e)
        {
            System.out.println("updateTask() error: "+e.getMessage());
            e.printStackTrace();
        }  finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("updateTaskError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public String statusCheck(int n)
    {
        try
        {
            conn=DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();

            resultSet=stmnt.executeQuery("SELECT tStatus FROM task WHERE tPId="+n+";");
            int flag=1;
            while(resultSet.next())
            {
                String s=resultSet.getString(1);
                if(s.equals("Open"))
                {
                    flag=0;
                    break;
                }
            }
            if(flag==0)
                return "Open";
            else
                return "Closed";

        }catch (SQLException e)
        {
            System.out.println("statusCheck() error: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("statusCheckError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    //<*******************************************************************************************>
    //employee dash board
    //project

    public List<Integer> projEmpListCombo()
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT * FROM name;");
            int n=resultSet.getInt(1);
            resultSet=stmnt.executeQuery("SELECT pe_pId FROM projEmp WHERE pe_eId="+n+";");

            List<Integer> projList=new ArrayList<>();
            while(resultSet.next())
            {
                int p=resultSet.getInt("pe_pId");
                projList.add(p);
            }
            Collections.sort(projList);
            return projList;

        }catch (SQLException e)
        {
            System.out.println("projEmpListCombo() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("projEmpListComboError(): "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public int nameReturn() {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT * FROM name;");
            int n=resultSet.getInt(1);
            return n;
        }
        catch (SQLException e)
        {
            System.out.println("projEmpListCombo() error: " + e.getMessage());
            return 0;
        }
        finally {
            try {
                if (stmnt != null)
                    stmnt.close();
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.out.println("projEmpListComboError(): " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    //<*******************************************************************************************>
    //Task
    public List<Integer> taskListComboProjEmp(int n, int empId)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:myDatabase.db");
            stmnt=conn.createStatement();
            resultSet=stmnt.executeQuery("SELECT tId FROM task WHERE tPId="+n+" and tEId="+empId+";");

            List<Integer> taskList=new ArrayList<>();
            while(resultSet.next())
            {
                taskList.add(resultSet.getInt(1));
            }
            return taskList;

        }catch (SQLException e)
        {
            System.out.println("taskListComboProj() error: "+e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                if(stmnt!=null)
                    stmnt.close();
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e)
            {
                System.out.println("taskListComboProjError(): "+e.getMessage());
                e.printStackTrace();
            }
        }

    }
}