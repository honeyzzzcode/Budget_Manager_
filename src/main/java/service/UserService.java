package service;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Money;
import model.User;
import repository.DBHandler;

public class UserService {
  private final Connection connection = DBHandler.getConnection();

  public int verifyUserDetails(String username, String password) throws Exception {
    String query = "SELECT id FROM users WHERE userName = ? && password = ? LIMIT 1";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, username);
    statement.setString(2, password);

    ResultSet result = statement.executeQuery();

    Integer userId = null;
    if (result.next()) userId = result.getInt("id");


    if (userId == null || userId == 0) throw new Exception("Username and password not correct");
    DBHandler.close(result, statement, connection);
    return userId;

  }

  public User getUserProfile(int userId) throws Exception {
    String query = "SELECT id, name, userName, email, phone, budget, createdAt, updatedAt "
        + "FROM users WHERE id = ? LIMIT 1";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, userId);

    ResultSet result = statement.executeQuery();

    User user = null;
    if (result.next()) {
      user = new User(
          result.getInt("id"), result.getString("name"), result.getString("userName"),
          result.getString("phone"), result.getString("email"),result.getFloat("budget"),
           result.getTimestamp("createdAt"), result.getTimestamp("updatedAt")
      );
    }
    DBHandler.close(result, statement, connection);

    if (user == null || userId == 0) throw new Exception("Username and password not correct");

    return user;
  }

  public void registerUser(User user) throws Exception {
    String query = "INSERT INTO users(name, userName, password,budget,email, phone  ) VALUES(?,?,?,?,?,?)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, user.getName());
    statement.setString(2, user.getUserName());
    statement.setString(3, user.getPassword());
    statement.setFloat(4, user.getBudget());
    statement.setString(5, user.getEmail());
    statement.setString(6, user.getPhone());



    statement.executeUpdate();
    DBHandler.close(statement, connection);
  }

  public ArrayList<User> getAllUsers() throws Exception {

    ArrayList<User> users = new ArrayList<>();
    String query = "SELECT id, name, userName, email, phone, budget, createdAt, updatedAt FROM users";
    PreparedStatement statement = connection.prepareStatement(query);

    ResultSet result = statement.executeQuery();
    while (result.next()) {
      users.add(new User(
          result.getInt("id"), result.getString("name"), result.getString("userName"),
           result.getString("phone"), result.getString("email"),result.getFloat("budget"),
          result.getTimestamp("createdAt"), result.getTimestamp("updatedAt")
      ));
    }

    DBHandler.close(result, statement, connection);
    return users;
  }

  public void addIncome(Money money) throws Exception {
    String query = "INSERT INTO money(inOrOut, category, amount  ) VALUES(?,?,?)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, money.getInOrOut());
    statement.setString(2, money.getCategory());
    statement.setFloat(3, money.getAmount());



    statement.executeUpdate();
    DBHandler.close(statement, connection);
  }

  public void addExpense(Money money)throws Exception {
    String query = "INSERT INTO money(inOrOut, category, amount  ) VALUES(?,?,?)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, money.getInOrOut());
    statement.setString(2, money.getCategory());
    statement.setFloat(3, money.getAmount());



    statement.executeUpdate();
    DBHandler.close(statement, connection);

  }
}
