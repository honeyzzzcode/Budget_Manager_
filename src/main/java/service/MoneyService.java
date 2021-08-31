package service;

import Types.Category;
import model.Money;
import model.User;
import repository.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
        String query = "SELECT * " + "FROM money WHERE userID = ? " + "ORDER BY inOrOut";
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

    //еще не закончила
    public ArrayList<Money> getCategoryRecord(int userId) throws Exception {
        userID=userId;
        ArrayList<Money> moneyRecords = new ArrayList<>();
        connection = DBHandler.getConnection();
        String query = "SELECT * " + "FROM money WHERE userID = ? , category = ?";
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

    //показыват сумму по доходам и расходам в одной таблице
    public ArrayList<Money> getTotalReport(int userId) throws Exception {
        userID=userId;
        ArrayList<Money> moneyRecords = new ArrayList<>();
        connection = DBHandler.getConnection();
        String query = "SELECT inOrOut, sum (amount) " + "FROM money WHERE userID = ? GROUP BY inOrOut";
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
}
