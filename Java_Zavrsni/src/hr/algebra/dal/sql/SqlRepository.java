package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.models.Movie;
import hr.algebra.models.MoviePerson;
import hr.algebra.models.User;
import hr.algebra.models.enums.MoviePersonType;
import hr.algebra.models.enums.UserRole;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlRepository implements Repository {

    private static final String ID_USER = "IDKorisnik";
    private static final String USERNAME = "KorisnickoIme";
    private static final String PASSWORD = "Lozinka";
    private static final String USER_ROLE = "KorisnikRazinaID";
    
    private static final String LOGIN_USER = "{ CALL LoginUser(?, ?) }";
    private static final String REGISTER_USER = "{ CALL RegisterUser(?, ?, ?) }";
    private static final String DELETE_ALL_NON_USER_RELATED_DATA = "{ CALL DeleteAllNonUserRelatedData }";
    private static final String INSERT_MOVIE = "{ CALL InsertMovie(?, ?, ?, ?, ?) }";
    private static final String INSERT_ACTOR_FOR_MOVIE = "{ CALL InsertActorForMovie(?, ?, ?) }";
    private static final String INSERT_DIRECTOR_FOR_MOVIE = "{ CALL InsertDirectorForMovie(?, ?, ?) }";
    private static final String INSERT_MOVIE_GENRE = "{ CALL InsertMovieGenre(?, ?) }";
    private static final String INSERT_ACTOR = "{ CALL InsertActor(?, ?, ?) }";
    private static final String INSERT_DIRECTOR = "{ CALL InsertDirector(?, ?, ?) }";
    private static final String SELECT_MOVIES = "{ CALL SelectMovies }";
    private static final String SELECT_ACTORS = "{ CALL SelectActors(?) }";
    private static final String SELECT_DIRECTORS = "{ CALL SelectDirectors(?) }";
    private static final String SELECT_GENRES = "{ CALL SelectGenres(?) }";
    private static final String SELECT_ALL_OTHER_ACTORS = "{ CALL SelectAllOtherActors(?) }";
    private static final String SELECT_ALL_OTHER_DIRECTORS = "{ CALL SelectAllOtherDirectors(?) }";
    private static final String ASSIGN_PERSON_TO_A_MOVIE = "{ CALL AssignPersonToAMovie(?, ?) }";
    private static final String REMOVE_PERSON_FROM_A_MOVIE = "{ CALL RemovePersonFromAMovie(?, ?) }";
    private static final String UPDATE_MOVIE = "{ CALL UpdateMovie(?, ?, ?, ?, ?) }";
    private static final String REMOVE_GENRES_FROM_A_MOVIE = "{ CALL RemoveGenresFromMovie(?) }";
    private static final String DELETE_MOVIE = "{ CALL DeleteMovie(?) }";
    private static final String SELECT_ALL_ACTORS = "{ CALL SelectAllActors }";
    private static final String SELECT_ALL_DIRECTORS = "{ CALL SelectAllDirectors }";
    private static final String DELETE_MOVIE_PERSON = "{ CALL DeletePerson(?) }";
    private static final String UPDATE_MOVIE_PERSON = "{ CALL UpdatePerson(?, ?, ?) }";
    
    @Override
    public Optional<User> LoginUser(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
                
        try( Connection con = dataSource.getConnection(); 
                CallableStatement stmt = con.prepareCall(LOGIN_USER)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                
                if(rs.next()) {
                    return Optional.of(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD),
                            UserRole.getUserRole(rs.getInt(USER_ROLE))
                    ));
                }
            }
        }
        
        return Optional.empty();
    }

    @Override
    public int RegisterUser(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(REGISTER_USER)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            stmt.registerOutParameter(3, Types.BIT);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void DeleteAllNonUserRelatedData() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ALL_NON_USER_RELATED_DATA)) {
            
            org.apache.commons.io.FileUtils.cleanDirectory(new File("assets/movie_posters"));
            stmt.executeUpdate();
        }
    }

    @Override
    public void SaveMovies(List<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try(Connection con = dataSource.getConnection();
                CallableStatement stmtMovie = con.prepareCall(INSERT_MOVIE);
                CallableStatement stmtActors = con.prepareCall(INSERT_ACTOR_FOR_MOVIE);
                CallableStatement stmtDirectors = con.prepareCall(INSERT_DIRECTOR_FOR_MOVIE);
                CallableStatement stmtMovieGenre = con.prepareCall(INSERT_MOVIE_GENRE)) {
            
            for (Movie movie : movies) {
                stmtMovie.setString(1, movie.getTitle());
                stmtMovie.setString(2, movie.getDescription());
                stmtMovie.   setInt(3, movie.getDuration());
                stmtMovie.setString(4, movie.getPicturePath());
                
                stmtMovie.registerOutParameter(5, Types.INTEGER);
                
                stmtMovie.executeUpdate();
                
                int movieID = stmtMovie.getInt(5);
                if (movie.getActors() != null) {
                    for (MoviePerson actor : movie.getActors()) {
                        stmtActors.setString(1, actor.getFirstName());
                        stmtActors.setString(2, actor.getLastName());
                        stmtActors.setInt(3, movieID);
                        
                        stmtActors.executeUpdate();
                    }
                }
                if (movie.getDirectors() != null) {
                    for (MoviePerson director : movie.getDirectors()) {
                        stmtDirectors.setString(1, director.getFirstName());
                        stmtDirectors.setString(2, director.getLastName());
                        stmtDirectors.setInt(3, movieID);
                        
                        stmtDirectors.executeUpdate();
                    }
                }
                
                if (movie.getGenre() != null) {
                    for (String genre : movie.getGenre()) {
                        stmtMovieGenre.setString(1, genre);
                        stmtMovieGenre.setInt(2, movieID);
                        
                        stmtMovieGenre.executeUpdate();
                    }
                }
            }
        }
    }

    @Override
    public List<Movie> LoadMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIES);
                CallableStatement stmtActors = con.prepareCall(SELECT_ACTORS);
                CallableStatement stmtDirectors = con.prepareCall(SELECT_DIRECTORS);
                CallableStatement stmtGenres = con.prepareCall(SELECT_GENRES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int movieID = rs.getInt(1);
                
                stmtActors.setInt(1, movieID);
                ResultSet rsActors = stmtActors.executeQuery();
                
                List<MoviePerson> actors = new ArrayList<>();
                while (rsActors.next()) {
                    actors.add(new MoviePerson(rsActors.getInt(1), rsActors.getString(2), rsActors.getString(3), MoviePersonType.ACTOR));
                }
                
                stmtDirectors.setInt(1, movieID);
                ResultSet rsDirectors = stmtDirectors.executeQuery();
                
                List<MoviePerson> directors = new ArrayList<>();
                while (rsDirectors.next()) {                    
                    directors.add(new MoviePerson(rsDirectors.getInt(1), rsDirectors.getString(2), rsDirectors.getString(3), MoviePersonType.DIRECTOR));
                }
                
                stmtGenres.setInt(1, movieID);
                ResultSet rsGenres = stmtGenres.executeQuery();
                
                List<String> genres = new ArrayList<>();
                while (rsGenres.next()) {                    
                    genres.add(rsGenres.getString(1));
                }
                
                movies.add(new Movie(movieID, rs.getString(2), LocalDateTime.now(), "", directors, actors, rs.getString(3), genres, rs.getInt(4), rs.getString(5)));
            }
        }
        
        return movies;
    }

    @Override
    public int UpdateMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try(Connection con = dataSource.getConnection();
            CallableStatement stmtMovie = con.prepareCall(UPDATE_MOVIE);
            CallableStatement stmtRemoveGenre = con.prepareCall(REMOVE_GENRES_FROM_A_MOVIE);
            CallableStatement stmtGenre = con.prepareCall(INSERT_MOVIE_GENRE);) {
            
            stmtMovie.setInt(1, movie.getId());
            stmtMovie.setString(2, movie.getTitle());
            stmtMovie.setString(3, movie.getDescription());
            stmtMovie.setInt(4, movie.getDuration());
            stmtMovie.setString(5, movie.getPicturePath());
            
            int updateStatus = stmtMovie.executeUpdate();
            
            stmtRemoveGenre.setInt(1, movie.getId());
            stmtRemoveGenre.execute();
            
            for(String genre : movie.getGenre()) {
                stmtGenre.setString(1, genre);
                stmtGenre.setInt(2, movie.getId());
                
                stmtGenre.execute();
            }
            
            return updateStatus;
        }
    }

    @Override
    public int InsertMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        int movieID = -1;
        try(Connection con = dataSource.getConnection();
            CallableStatement stmtMovie = con.prepareCall(INSERT_MOVIE);
            CallableStatement stmtGenres = con.prepareCall(INSERT_MOVIE_GENRE)) {
            
            stmtMovie.setString(1, movie.getTitle());
            stmtMovie.setString(2, movie.getDescription());
            stmtMovie.setInt(3, movie.getDuration());
            stmtMovie.setString(4, movie.getPicturePath());
            
            stmtMovie.registerOutParameter(5, Types.INTEGER);
            
            stmtMovie.execute();
            
            movieID = stmtMovie.getInt(5);
            
            for(String genre : movie.getGenre()) {
                stmtGenres.setString(1, genre);
                stmtGenres.setInt(2, movieID);
                stmtGenres.execute();
            }
        }
        
        return movieID;
    }

    @Override
    public int InsertMoviePerson(MoviePerson person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        int personID = -1;
        try(Connection con = dataSource.getConnection();
            CallableStatement stmtActor = con.prepareCall(INSERT_ACTOR);
            CallableStatement stmtDirector = con.prepareCall(INSERT_DIRECTOR)) {
            
            if (person.getMoviePersonType().equals(MoviePersonType.ACTOR)) {
                stmtActor.setString(1, person.getFirstName());
                stmtActor.setString(2, person.getLastName());
                
                stmtActor.registerOutParameter(3, Types.INTEGER);

                stmtActor.execute();

                personID = stmtActor.getInt(3);
            } else {
                stmtDirector.setString(1, person.getFirstName());
                stmtDirector.setString(2, person.getLastName());
                
                stmtDirector.registerOutParameter(3, Types.INTEGER);

                stmtDirector.execute();

                personID = stmtDirector.getInt(3);
            }
        }
        
        return personID;
    }

    @Override
    public List<MoviePerson> SelectAllOtherActors(Movie movie) throws Exception {
        List<MoviePerson> actors = new ArrayList<>();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(SELECT_ALL_OTHER_ACTORS)) {
            
            stmt.setInt(1, movie.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    actors.add(new MoviePerson(rs.getInt(1), rs.getString(2), rs.getString(3), MoviePersonType.ACTOR));
                }
            }
        }
        
        return actors;
    }

    @Override
    public List<MoviePerson> SelectAllOtherDirectors(Movie movie) throws Exception {
        List<MoviePerson> directors = new ArrayList<>();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(SELECT_ALL_OTHER_DIRECTORS)) {
            
            stmt.setInt(1, movie.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    directors.add(new MoviePerson(rs.getInt(1), rs.getString(2), rs.getString(3), MoviePersonType.DIRECTOR));
                }
            }
        }
        
        return directors;
    }

    @Override
    public void AssignPersonToAMovie(MoviePerson person, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(ASSIGN_PERSON_TO_A_MOVIE)) {
            
            stmt.setInt(1, person.getId());
            stmt.setInt(2, movie.getId());
            
            stmt.execute();
        }
    }

    @Override
    public void RemovePersonFromAMovie(MoviePerson person, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(REMOVE_PERSON_FROM_A_MOVIE)) {
            
            stmt.setInt(1, person.getId());
            stmt.setInt(2, movie.getId());
            
            stmt.execute();
        }
    }

    @Override
    public void DeleteMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {
            
            stmt.setInt(1, movie.getId());
            stmt.execute();
        }
    }

    @Override
    public List<MoviePerson> SelectAllActors() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        List<MoviePerson> actors = new ArrayList<>();
        
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(SELECT_ALL_ACTORS);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {

                actors.add(new MoviePerson(
                        rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        MoviePersonType.ACTOR)
                );
            }
        }
        return actors;
    }

    @Override
    public List<MoviePerson> SelectAllDirectors() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        List<MoviePerson> directors = new ArrayList<>();
        
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(SELECT_ALL_DIRECTORS);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                directors.add(new MoviePerson(
                        rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        MoviePersonType.DIRECTOR)
                );
            }
        }
        return directors;
    }

    @Override
    public void DeleteMoviePerson(MoviePerson person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(DELETE_MOVIE_PERSON)) {
            
            stmt.setInt(1, person.getId());
            stmt.execute();
        }
    }

    @Override
    public int UpdateMoviePerson(MoviePerson person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        int updateStatus = 0;
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(UPDATE_MOVIE_PERSON)) {
            
            stmt.setInt(1, person.getId());
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getLastName());
            
            updateStatus = stmt.executeUpdate();
        }
        
        return updateStatus;
    }
    
}