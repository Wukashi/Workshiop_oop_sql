package pl.coderslab.workshop;
import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException {
        User [] users = new User[0];
        UserDao userDao = new UserDao();
        users = userDao.findAll();
        for (int i = 0; i < users.length; i++) {
            System.out.println("id: " + users[i].getId() + " username: " + users[i].getUsername() + " email: " + users[i].getEmail()+ " password: "+ users[i].getPassword());
        }
    }
}
