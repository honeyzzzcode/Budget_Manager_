package service;

import Types.Category;
import controller.CategoryReportController;
import model.Money;
import model.User;
import repository.DBHandler;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static jdk.nashorn.internal.parser.TokenType.AND;

public class MoneyService {

    int userID;
    private  Connection connection = DBHandler.getConnection();

    public User getUserProfile(int userId) throws Exception {
        userID=userId;
        connection = DBHandler.getConnection();
        String query = "SELECT id, name, userName, email, phone, budget, createdAt, updatedAt "
                + "FROM users WHERE id = ? LIMIT 1";
        Connection connection1 = DBHandler.getConnection();
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, userId);

        ResultSet result = statement.executeQuery();

        User user = null;
        if (result.next()) {
            user = new User(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("userName"),
                    result.getString("phone"),
                    result.getString("email"),
                    result.getFloat("budget"),
                    result.getTimestamp("createdAt"),
                    result.getTimestamp("updatedAt")
            );
        }
        DBHandler.close(result, statement, connection);

        if (user == null || userId == 0) throw new Exception("Username and password not correct");

        return user;
    }

    public void addIncome(Money money) throws Exception {
        connection = DBHandler.getConnection();
        String query = "INSERT INTO money(inOrOut, category, amount ,userID ) VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, money.getInOrOut());
        statement.setString(2, money.getCategory());
        statement.setFloat(3, money.getAmount());
        statement.setInt(4,userID);

        statement.executeUpdate();
        DBHandler.close(statement, connection);
    }

    public void addExpense(Money money) throws Exception {
        connection = DBHandler.getConnection();
        String query = "INSERT INTO money(inOrOut, category, amount ,userID ) VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, money.getInOrOut());
        statement.setString(2, money.getCategory());
        statement.setFloat(3, money.getAmount());
        statement.setInt(4,userID);

        statement.executeUpdate();
        DBHandler.close(statement, connection);

    }

    public void updateBudget(User user,int userId) throws Exception{
        connection = DBHandler.getConnection();
        String query = "UPDATE users SET budget = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setFloat(1,user.getBudget());
        statement.setInt(2,userId);
        statement.executeUpdate();
        DBHandler.close(statement, connection);

    }

    public ArrayList<Money> getAllMoneyRecords(int userId) throws Exception {
        userID=userId;
        ArrayList<Money> moneyRecords = new ArrayList<>();
        connection = DBHandler.getConnection();
        String query = "SELECT * " + "FROM money WHERE userID = ? ";
        Connection connection1 = DBHandler.getConnection();
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            moneyRecords.add(new Money(
                    result.getInt("id"),
                    result.getString("inOrOut"),
                    result.getString("category"),
                    result.getFloat("amount"),
                    result.getInt("userID"),
                    result.getTimestamp("createdAt"),
                    result.getTimestamp("updatedAt")
            ));
        }
        DBHandler.close(result, statement, connection);
        System.out.println(moneyRecords.size());
        return moneyRecords;
    }

   public void updateRecord(int userId) throws Exception {
        userID=userId;
        ArrayList<Money> moneyArrayList = new ArrayList<>();
        connection = DBHandler.getConnection();
        String query = "UPDATE money SET createdAt = ? AND inOrOut = ? AND amount = ? AND category = ? WHERE userID = ?";
        Connection connection1 = DBHandler.getConnection();
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, userId);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            moneyArrayList.add(new Money(
                    result.getInt("id"),
                    result.getString("inOrOut"),
                    result.getString("category"),
                    result.getFloat("amount"),
                    result.getInt("userID"),
                    result.getTimestamp("createdAt"),
                    result.getTimestamp("updatedAt")
            ));
        }
        DBHandler.close(result, statement, connection);

    }
    public void deleteRecord(int ID , int userId) throws Exception {
        userID=userId;
        ArrayList<Money> moneyRecords = new ArrayList<>();
        connection = DBHandler.getConnection();
        String query = "DELETE FROM money WHERE id = ? AND userID = ?";
        Connection connection1 = DBHandler.getConnection();
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, ID);
        statement.setInt(2, userId);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            moneyRecords.add(new Money(
                    result.getInt("id"),
                    result.getString("inOrOut"),
                    result.getString("category"),
                    result.getFloat("amount"),
                    result.getInt("userID"),
                    result.getTimestamp("createdAt"),
                    result.getTimestamp("updatedAt")
            ));
        }
        DBHandler.close(result, statement, connection);

    }

    public ArrayList<Money> getCategoryRecord(int userId, String category) throws Exception {
        userID=userId;
        ArrayList<Money> moneyRecords = new ArrayList<>();
        connection = DBHandler.getConnection();
        String query = "SELECT * " + "FROM money WHERE userID = ? AND category = ?";
        Connection connection1 = DBHandler.getConnection();
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, userId);
        statement.setString(2, category);

        ResultSet result = statement.executeQuery();


        while (result.next()) {
            moneyRecords.add(new Money(
                    result.getTimestamp("createdAt"),
                    result.getString("inOrOut"),
                    result.getFloat("amount")



            ));
        }
        DBHandler.close(result, statement, connection);
        System.out.println();
        return moneyRecords;
    }

    public ArrayList<Money> getTotalReport(int userId) throws Exception {
        userID=userId;
        ArrayList<Money> moneyRecords = new ArrayList<>();
        connection = DBHandler.getConnection();
        String query = "SELECT inOrOut, sum(amount) " + "FROM money WHERE userID = ? GROUP BY inOrOut";
        Connection connection1 = DBHandler.getConnection();
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, userId);

        ResultSet result = statement.executeQuery();


        while (result.next()) {
            moneyRecords.add(new Money(
                         result.getString("inOrOut"),
                         result.getFloat("sum(amount)")
            ));
        }
        DBHandler.close(result, statement, connection);
       return moneyRecords;
    }

    public Float  getMoney(int userId) throws Exception {
        userID=userId;
        connection = DBHandler.getConnection();
        String query = "SELECT  budget "
                + "FROM users WHERE id = ? LIMIT 1";
        Connection connection1 = DBHandler.getConnection();
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, userId);

        ResultSet result = statement.executeQuery();

        Float budget = null;
        while (result.next()) {
              budget= result.getFloat("budget");

        }
        DBHandler.close(result, statement, connection);
        return budget;
    }

    public ArrayList<Money> getReportByDate(int userId, LocalDate startDate, LocalDate endDate ) throws Exception {
        userID=userId;
        ArrayList<Money> moneyRecords = new ArrayList<>();
        connection = DBHandler.getConnection();
        String query = "SELECT * " + "FROM money WHERE userID = ? AND createdAt > ? ' 00:00:00' AND createdAt < ? ' 23:59:59'";
        Connection connection1 = DBHandler.getConnection();
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, userId);
        statement.setDate(2, Date.valueOf(startDate));
        statement.setDate(3, Date.valueOf(endDate));

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            moneyRecords.add(new Money(
                    result.getInt("id"),
                    result.getString("inOrOut"),
                    result.getString("category"),
                    result.getFloat("amount"),
                    result.getInt("userID"),
                    result.getTimestamp("createdAt"),
                    result.getTimestamp("updatedAt")
            ));
        }
        DBHandler.close(result, statement, connection);
        System.out.println();
        return moneyRecords;
}


}
