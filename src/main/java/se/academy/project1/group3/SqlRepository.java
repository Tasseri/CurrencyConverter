package se.academy.project1.group3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 9/29/2016.
 */
@Component
public class SqlRepository implements Repository {

    @Autowired
    DataSource dataSource;

    public void addTransaction(long userID, String currencyFrom, String currencyTo, long amount, long result){
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Transactions (User_ID, CurrencyFrom, CurrencyTo, Amount, Result) " +
                     "VALUES (?,?,?, ?, ?)")) {
            ps.setLong(1, userID);
            ps.setString(2, currencyFrom);
            ps.setString(3, currencyTo);
            ps.setLong(4, amount);
            ps.setLong(5, result);
            try {
                ps.executeUpdate();
            }catch (SQLException e){
                throw new CurrencyConverterException(e);
                //OBS! Check this!
            }
        } catch (SQLException e) {
            throw new CurrencyConverterException(e);
        }
    }//End addTransaction

    public List<Transaction> listTransactions() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT TOP (5) T.TransID, U.FirstName, U.LastName, U.UserName, T.CurrencyFrom, T.CurrencyTo, T.Amount, T.Result, T.Date FROM Transactions as T INNER JOIN Users as U ON T.User_ID = U.UserID ORDER BY T.Date DESC")) {
            List<Transaction> transactions = new ArrayList<>();
            while (rs.next()) transactions.add(rsTransaction(rs));
            return transactions;
        } catch (SQLException e) {
            throw new CurrencyConverterException(e);
        }
    }
    public List<Transaction> listTransUser(String username) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT T.TransID, U.FirstName, U.LastName, U.UserName, T.CurrencyFrom, T.CurrencyTo, T.Amount, T.Result, T.Date FROM Transactions as T INNER JOIN Users as U ON T.User_ID = U.UserID WHERE U.UserName = ? ORDER BY T.Date DESC")){
             ps.setString(1, username);

            List<Transaction> transactions = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) transactions.add(rsTransaction(rs));
            return transactions;
        } catch (SQLException e) {
            throw new CurrencyConverterException(e);
        }
    }



    public List<Transaction> topTransactions() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as Number, U.UserName FROM Transactions AS T INNER JOIN Users as U ON T.User_ID = U.UserID WHERE T.User_ID IN (SELECT User_ID FROM Transactions AS Tr) GROUP BY U.UserName ORDER BY Number DESC")) {
            List<Transaction> transactions = new ArrayList<>();
            while (rs.next()) transactions.add(rsTopTransaction(rs));
            return transactions;
        } catch (SQLException e) {
            throw new CurrencyConverterException(e);
        }
    }

    @Override
    public void addUser(String firstName, String lastName, String username, String password) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Users (FirstName, LastName, Username, PasswordHash) " +
                     "VALUES (?,?,?,HASHBYTES('SHA2_512', ?))")) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, username);
            ps.setString(4, password);
            try {
                ps.executeUpdate();
            }catch (SQLException e){
                throw new CurrencyConverterException(e);
            }
        } catch (SQLException e) {
            throw new CurrencyConverterException(e);
        }
    }

    @Override
    public boolean loginUser(String username, String password) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT *" +
                     "FROM Users "+
                     "WHERE ? = Users.username AND HASHBYTES('SHA2_512', ?) = Users.passwordHash")) {
            ps.setString(1, username);
            ps.setString(2, password);
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return true;
                }
                return false;
            }catch (SQLException e){
                throw new CurrencyConverterException(e);
            }
        } catch (SQLException e) {
            throw new CurrencyConverterException(e);
        }
    }

    private Transaction rsTransaction(ResultSet rs) throws SQLException {
        return new Transaction(rs.getLong("TransID"), rs.getString("FirstName"),
                rs.getString("LastName"),rs.getString("UserName"),
                rs.getString("CurrencyFrom"), rs.getString("CurrencyTo"),
                rs.getLong("Amount"), rs.getLong("Result"), rs.getTimestamp("Date").toLocalDateTime());
    }

    private Transaction rsTopTransaction(ResultSet rs) throws SQLException {
        return new Transaction(rs.getLong("Number"), rs.getString("UserName"));
    }

}
