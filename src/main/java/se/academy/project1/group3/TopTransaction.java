package se.academy.project1.group3;

/**
 * Created by Administrator on 2016-09-30.
 */
public class TopTransaction {

    long number;
    String userName;

    public TopTransaction(long number, String userName) {
        this.number = number;
        this.userName = userName;
    }

    public long getNumber() {
        return number;
    }

    public String getUserName() {
        return userName;
    }
}
