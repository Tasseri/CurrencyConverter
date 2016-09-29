package se.academy.project1.group3;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2016-09-28.
 */
public class Transaction {

    private final long transId;
    private final long userId;
    private final String currencyFrom;
    private final String currencyTo;
    private final long amount;
    private final long result;
    private final LocalDateTime date;

    public Transaction(long transId, long userId, String currencyFrom, String currencyTo, long amount, long result, LocalDateTime date) {
        this.transId = transId;
        this.userId = userId;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.result = result;
        this.date = date;
    }

    public long getTransId() {
        return transId;
    }

    public long getUserId() {
        return userId;
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
}
