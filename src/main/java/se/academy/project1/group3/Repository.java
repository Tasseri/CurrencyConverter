package se.academy.project1.group3;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Administrator on 9/29/2016.
 */
public interface Repository {
    void addTransaction(long userID, String currencyFrom, String currencyTo, long amount, long result);
    List<Transaction> listTransactions();
    List<Transaction> topTransactions();
    void addUser(String firstName, String lastName, String username, String password);
    boolean loginUser(String username, String password);
    List<Transaction> listTransUser(String username);
}
