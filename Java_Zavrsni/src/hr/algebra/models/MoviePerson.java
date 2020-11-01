package hr.algebra.models;

import hr.algebra.models.enums.MoviePersonType;
import java.util.Objects;

public class MoviePerson implements Comparable<MoviePerson>{
    private int id;
    private String firstName;
    private String lastName;
    private MoviePersonType moviePersonType;

    public MoviePerson(int id, String firstName, String lastName, MoviePersonType moviePersonType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.moviePersonType = moviePersonType;
    }

    public MoviePerson(String firstName, String lastName, MoviePersonType moviePersonType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.moviePersonType = moviePersonType;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public MoviePersonType getMoviePersonType() {
        return moviePersonType;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMoviePersonType(MoviePersonType moviePersonType) {
        this.moviePersonType = moviePersonType;
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.firstName);
        hash = 83 * hash + Objects.hashCode(this.lastName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MoviePerson other = (MoviePerson) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (this.moviePersonType != other.moviePersonType) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(MoviePerson o) {
        return this.firstName.compareTo(o.firstName);
    }
    
}