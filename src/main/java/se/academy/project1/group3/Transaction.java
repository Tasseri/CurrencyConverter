package se.academy.project1.group3;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2016-09-28.
 */


public class Transaction {

    private  long transId;
    private  String firstName;
    private  String lastName;
    private  String userName;
    private  String currencyFrom;
    private  String currencyTo;
    private  long amount;
    private  long result;
    private  LocalDateTime date;

    private long number;

    public Transaction(long transId, String firstName, String lastName, String userName, String currencyFrom, String currencyTo, long amount, long result, LocalDateTime date) {
        this.transId = transId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.result = result;
        this.date = date;

    }

    public Transaction(long number, String userName) {
        this.number = number;
        this.userName = userName;
    }

    public long getTransId() {
        return transId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public long getAmount() {
        return amount;
    }

    public long getResult() {
        return result;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public long getNumber() {
        return number;
    }

}
