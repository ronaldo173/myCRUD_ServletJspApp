package ua.ronaldo173.dao;

import ua.ronaldo173.model.User;
import ua.ronaldo173.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * it contains the logic for database operation
 */
public class UserDao {
    private Connection connection;

    public UserDao() {
        connection = DbUtil.getConnection();
    }

    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users(firstname, lastname, dob, email) values (?,?,?,?)");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, new Date(user.getDob().getTime()));
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void deleteUser(int userId) {

    }

    public void updateUser(User user) {

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userid"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setDob(rs.getDate("dob"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from users where userid=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserId(userId);
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastname"));
                user.setDob(rs.getDate("dob"));
                user.setEmail(rs.getString("email"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
