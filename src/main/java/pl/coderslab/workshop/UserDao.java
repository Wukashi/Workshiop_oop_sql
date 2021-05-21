package pl.coderslab.workshop;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO users (username, email, password) VALUES (?,?,?)";
    private static final String SET_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL_USER_QUERY = "SELECT * FROM users"; //ResultSet

    public User create(User user) throws SQLException {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, hashPassword(user.getPassword()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
                user.setId(resultSet.getInt(1));
            return user;
    }
    public  User read(int id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_QUERY);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(!resultSet.next())
            return null;
        else {
            String userName = resultSet.getString(2);
            String email = resultSet.getString(3);
            String pass = resultSet.getString(4);
            return new User(id, userName, email, pass);
        }
    }
    public  void update(User user)
    {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SET_QUERY);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void delete(int userId)
    {
        try {
            Connection connection =DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public User[] findAll()
    {
        try {
            Connection connection = DBUtil.getConnection();
            User[] users = new User[0];
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String mail = resultSet.getString(3);
                String pass = resultSet.getString(4);
                User u = new User(id, name,mail, pass);
                users = addToList(users, u);
            }
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    private static User[] addToList(User[] usersTab, User user)
    {
        usersTab = Arrays.copyOf(usersTab, usersTab.length +1 );
        usersTab[usersTab.length - 1] = user;
        return usersTab;
    }
}
