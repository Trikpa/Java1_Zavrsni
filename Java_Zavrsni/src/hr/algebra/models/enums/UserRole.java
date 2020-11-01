package hr.algebra.models.enums;

public enum UserRole {
    ADMIN, USER;
    
    public static UserRole getUserRole(int userRoleID) {
        switch (userRoleID) {
            case 1:
                return UserRole.ADMIN;
            case 2:
                return UserRole.USER;
            default:
                return UserRole.USER;
        }
    }
}