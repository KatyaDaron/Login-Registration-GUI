import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DashboardForm extends JFrame {
    private JPanel dashboardPanel;
    private JLabel lbAdmin;

    public DashboardForm() {
        setTitle("Dashboard");
        setContentPane(dashboardPanel);
        setMinimumSize(new Dimension(500, 429));
        setSize(1200, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        boolean hasRegisteredUsers = connectToDatabase();
        if (hasRegisteredUsers) {
            LoginForm loginForm = new LoginForm(this);
            User user = loginForm.user;

            if (user !=null) {
                lbAdmin.setText("User: " + user.name);
                setVisible(true);
            }
            else {
                dispose();
            }
        }
        else {
            RegForm regForm = new RegForm(this);
            User user = regForm.user;

            if (user !=null) {
                lbAdmin.setText("User: " + user.name);
                setVisible(true);
            }
            else {
                dispose();
            }
        }
    }

    private boolean connectToDatabase() {
        boolean hasRegisteredUsers = false;

        final String MYSQL_SERVER_URL = "jdbc:mysql://localhost/";
        final String DB_URL = "jdbc:mysql://localhost/user";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            //Connect to MySQL sever and create database if not created
            Connection conn = DriverManager.getConnection(MYSQL_SERVER_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS user");
            statement.close();
            conn.close();

            //Connect to database and create the table "registered" if not created
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS registered ("
            + "name VARCHAR(20) NOT NULL,"
            + "email VARCHAR(20),"
            + "phone VARCHAR(20),"
            + "address VARCHAR(20),"
            + "password VARCHAR(20)"
            + ")";
            statement.executeUpdate(sql);

            //Check if we have users in the table "registered"
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM registered");

            if (resultSet.next()) {
                int numUsers = resultSet.getInt(1);
                if (numUsers > 0) {
                    hasRegisteredUsers = true;
                }
            }

            statement.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return hasRegisteredUsers;
    }

    public static void main(String[] args) {
        DashboardForm myForm = new DashboardForm();
    }
}
