package hr.algebra.models;

import hr.algebra.models.enums.MoviePersonType;
import java.time.LocalDateTime;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {
    
    private int id;
    private String title;
    private LocalDateTime publishDate;
    private String originalTitle;
    
    @XmlElementWrapper
    @XmlElement(name="director")
    private List<MoviePerson> directors;
    
    @XmlElementWrapper
    @XmlElement(name="actor")
    private List<MoviePerson> actors;
    
    private String description;
    private List<String> genre;
    private int duration;
    private String picturePath;

    public Movie() {
    }

    public Movie(String title, LocalDateTime publishDate, String originalTitle, List<MoviePerson> directors, List<MoviePerson> actors, String description, List<String> genre, int duration, String picturePath) {
        this.title = title;
        this.publishDate = publishDate;
        this.originalTitle = originalTitle;
        this.directors = directors;
        this.actors = actors;
        this.description = description;
        this.genre = genre;
        this.duration = duration;
        this.picturePath = picturePath;
    }
    
    public Movie(int id, String title, LocalDateTime publishDate, String originalTitle, List<MoviePerson> directors, List<MoviePerson> actors, String description, List<String> genre, int duration, String picturePath) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
        this.originalTitle = originalTitle;
        this.directors = directors;
        this.actors = actors;
        this.description = description;
        this.genre = genre;
        this.duration = duration;
        this.picturePath = picturePath;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<MoviePerson> getDirectors() {
        return directors;
    }

    public void setDirectors(List<MoviePerson> directors) {
        this.directors = directors;
    }

    public Boolean addDirector(MoviePerson director) {
        if(director.getMoviePersonType() != MoviePersonType.DIRECTOR)
            return false;
        
        for(MoviePerson dir : this.directors)
            if (dir.equals(director))
                return false;
        
        return directors.add(director);
    }
    
    public Boolean removeDirector(MoviePerson director) {
        if(director.getMoviePersonType() != MoviePersonType.DIRECTOR)
            return false;
        
        if (!this.directors.contains(director))
            return false;
        
        return this.directors.remove(director);
    }
    
    public List<MoviePerson> getActors() {
        return actors;
    }

    public void setActors(List<MoviePerson> actors) {
        this.actors = actors;
    }
    
    public Boolean addActor(MoviePerson actor) {
        if(actor.getMoviePersonType() != MoviePersonType.ACTOR)
            return false;
        
        for(MoviePerson act : this.actors)
            if (act.equals(actor))
                return false;
        
        return actors.add(actor);
    }
    
    public Boolean removeActor(MoviePerson actor) {
        if(actor.getMoviePersonType() != MoviePersonType.ACTOR)
            return false;
        
        if (!this.actors.contains(actor))
            return false;
        
        return this.actors.remove(actor);
    }
    
    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
    
    @Override
    public String toString() {
        return title;
    }
}