package hr.algebra.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import hr.algebra.models.Movie;
import hr.algebra.models.MoviePerson;
import hr.algebra.models.enums.MoviePersonType;
import hr.algebra.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.jsoup.Jsoup;

public class MovieParser {
    
    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=1";
    private static final int TIMEOUT = 10000;
    private static final String REQUEST_METHOD = "GET";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets/movie_posters";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;
    private static final Random RANDOM = new Random();
    
    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();
        
        HttpURLConnection connection = UrlConnectionFactory.getHttpUrlConnection(RSS_URL, TIMEOUT, REQUEST_METHOD);
        
        if (connection.getResponseCode() != 200) {
            return null;
        }
        
        XMLEventReader xmlReader = ParserFactory.createStaxParser(connection.getInputStream());
        
        Optional<TagType> tagType = Optional.empty();
        Movie movie = null;
        StartElement startElement = null;
        
        while (xmlReader.hasNext()) {
            XMLEvent event = xmlReader.nextEvent();
            
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();
                 
                    tagType = TagType.from(qName);
                    
                    if (tagType.isPresent() && TagType.ITEM == tagType.get()) {
                        movie = new Movie();
                        movies.add(movie);
                    }
                    
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if (tagType.isPresent()) {
                        Characters characters = event.asCharacters();
                        String data = characters.getData().trim();
                        switch (tagType.get()) {
                            case TITLE:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setTitle(data);
                                }
                                break;
                            case PUBLISH_DATE:
                                if (movie != null && !data.isEmpty()) {
                                    LocalDateTime publishedDate = LocalDateTime.parse(data, DATE_FORMATTER);
                                    movie.setPublishDate(publishedDate);
                                }
                                break;
                            case DESCRIPTION:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setDescription(Jsoup.parse(data).text());
                                }
                                break;
                            case ORIGINAL_TITLE:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setOriginalTitle(data);
                                }
                                break;
                            case DIRECTORS:
                                if (movie != null && !data.isEmpty()) {
                                    List<MoviePerson> directors = new ArrayList<>();
                                    
                                    parseActorsOrDirectors(directors, data, MoviePersonType.DIRECTOR);
                                    
                                    movie.setDirectors(directors);
                                }
                                break;
                            case ACTORS:
                                if (movie != null && !data.isEmpty()) {
                                    List<MoviePerson> actors = new ArrayList<>();
                                    
                                    parseActorsOrDirectors(actors, data, MoviePersonType.ACTOR);
                                    
                                    movie.setActors(actors);
                                }
                                break;
                            case DURATION:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setDuration(Integer.parseInt(data));
                                }
                                break;
                            case GENRE:
                                if (movie != null && !data.isEmpty()) {
                                    String[] genres = data.split(",");
                                    List<String> gStrings = new ArrayList<>();
                                    
                                    for (String genre : genres) {
                                        gStrings.add(genre.trim());
                                    }
                                    movie.setGenre(gStrings);
                                }
                                break;
                            case POSTER:
                                if (movie != null && !data.isEmpty()) {
                                    handlePicture(movie, data);
                                }
                                break;
                        }
                    }
                    break;
            }
        }
        
        return movies;
    }
    
    private static void parseActorsOrDirectors(List<MoviePerson> actorsOrDirectors, String data, MoviePersonType actorOrDirector) {
        if (data.contains(",")) {
            String[] actorsOrDirectorsArray = data.split(",");
            for (String director : actorsOrDirectorsArray) {
                String[] actorOrDirectorFullName = director.trim().split(" ");
                switch (actorOrDirectorFullName.length) {
                    case 1:
                        actorsOrDirectors.add(new MoviePerson(actorOrDirectorFullName[0].trim(), "", actorOrDirector));
                        break;
                    case 2:
                        actorsOrDirectors.add(new MoviePerson(actorOrDirectorFullName[0].trim(), actorOrDirectorFullName[1], actorOrDirector));
                        break;
                    case 3:
                        actorsOrDirectors.add(new MoviePerson(actorOrDirectorFullName[0].trim() + ' ' + actorOrDirectorFullName[1], actorOrDirectorFullName[2], actorOrDirector));
                        break;
                    default:
                        actorsOrDirectors.add(new MoviePerson(Arrays.toString(actorOrDirectorFullName).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", " ").trim(), "", actorOrDirector));
                        break;
                }
            }
        } else {
            String[] actorOrDirectorFullName = data.split(" ");
            switch (actorOrDirectorFullName.length) {
                    case 1:
                        actorsOrDirectors.add(new MoviePerson(actorOrDirectorFullName[0].trim(), "", actorOrDirector));
                        break;
                    case 2:
                        actorsOrDirectors.add(new MoviePerson(actorOrDirectorFullName[0].trim(), actorOrDirectorFullName[1], actorOrDirector));
                        break;
                    case 3:
                        actorsOrDirectors.add(new MoviePerson(actorOrDirectorFullName[0].trim() + ' ' + actorOrDirectorFullName[1], actorOrDirectorFullName[2], actorOrDirector));
                        break;
                    default:
                        actorsOrDirectors.add(new MoviePerson(Arrays.toString(actorOrDirectorFullName).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", " ").trim(), "", actorOrDirector));
                        break;
                }
        }
    }
    
    private static void handlePicture(Movie movie, String pictureUrl) throws IOException {
        pictureUrl = pictureUrl.replaceAll("http", "https");
        
        String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
        if (ext.length() > 4) {
            ext = EXT;
        }
        
        String pictureName = Math.abs(RANDOM.nextInt()) + ext;
        String localPicturePath = DIR + File.separator + pictureName; //
        
        FileUtils.copyFromUrl(pictureUrl, localPicturePath);
        
        movie.setPicturePath(localPicturePath);
    }
    
    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        LINK("link"),
        DESCRIPTION("description"),
        PUBLISH_DATE("pubDate"),
        ORIGINAL_TITLE("orignaziv"),
        ACTORS("glumci"),
        DURATION("trajanje"),
        GENRE("zanr"),
        POSTER("plakat"),
        DIRECTORS("redatelj");
        
        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }
}