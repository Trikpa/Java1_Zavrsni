package hr.algebra.models;

import hr.algebra.models.enums.UserRole;

public class User {
    private final int id;
    private final String username;
    private final String password;
    private final UserRole userLevel;

    public User(int id, String username, String password, UserRole userLevel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userLevel = userLevel;
    }

    public int getId() { return id; }

    public String getUsername() { return username; }

    public UserRole getUserLevel() { return userLevel; }

    @Override
    public String toString() { return username; }
}