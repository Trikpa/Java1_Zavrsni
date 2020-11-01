package hr.algebra.models.enums;

public enum MoviePersonType {
    DIRECTOR, ACTOR;
    
    public static MoviePersonType getUserRole(int moviePersonID) {
        switch (moviePersonID) {
            case 1:
                return MoviePersonType.DIRECTOR;
            case 2:
                return MoviePersonType.ACTOR;
            default:
                return MoviePersonType.ACTOR;
        }
    }
}