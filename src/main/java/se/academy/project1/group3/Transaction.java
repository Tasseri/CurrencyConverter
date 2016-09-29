package se.academy.project1.group3;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2016-09-28.
 */
public class Transaction {

    public final long transId;
    public final long userId;
    public final String currencyFrom;
    public final String currencyTo;
    public final long amount;
    public final long result;
    public final LocalDateTime date;

    public Transaction(long transId, long userId, String currencyFrom, String currencyTo, long amount, long result, LocalDateTime date) {
        this.transId = transId;
        this.userId = userId;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.result = result;
        this.date = date;
    }


}
