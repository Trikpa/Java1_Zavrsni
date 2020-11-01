package hr.algebra.dal;

import hr.algebra.models.Movie;
import hr.algebra.models.MoviePerson;
import hr.algebra.models.User;
import java.util.List;
import java.util.Optional;

public interface Repository {
    Optional<User> LoginUser(String username, String password) throws Exception;
    int RegisterUser(String username, String password) throws Exception;
    void DeleteAllNonUserRelatedData() throws Exception;
    void SaveMovies(List<Movie> movies) throws Exception;
    List<Movie> LoadMovies() throws Exception;
    int UpdateMovie(Movie movie) throws Exception;
    int InsertMovie(Movie movie) throws Exception;
    void DeleteMovie(Movie movie) throws Exception;
    int InsertMoviePerson(MoviePerson person) throws Exception;
    void DeleteMoviePerson(MoviePerson person) throws Exception;
    int UpdateMoviePerson(MoviePerson person) throws Exception;
    List<MoviePerson> SelectAllOtherActors(Movie movie) throws Exception;
    List<MoviePerson> SelectAllOtherDirectors(Movie movie) throws Exception;
    void AssignPersonToAMovie(MoviePerson person, Movie movie) throws Exception;
    void RemovePersonFromAMovie(MoviePerson person, Movie movie) throws Exception;
    List<MoviePerson> SelectAllActors() throws Exception;
    List<MoviePerson> SelectAllDirectors() throws Exception;
}
