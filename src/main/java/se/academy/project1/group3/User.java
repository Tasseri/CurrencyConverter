package se.academy.project1.group3;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 9/29/2016.
 */
public class User {
    private long userId;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDateTime created;
    private LocalDateTime lastLogin;

    public User(long userId, String firstName, String lastName, String username, LocalDateTime created, LocalDateTime lastLogin) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.created = created;
        this.lastLogin = lastLogin;
    }
}
