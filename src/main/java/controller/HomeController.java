package controller;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Movie;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class HomeController implements IController {

    public void process( final HttpServletRequest request, final HttpServletResponse response, final ServletContext servletContext, final ITemplateEngine templateEngine)  throws Exception {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("site_name", "Mflix");


        ConnectionString connectionString = new ConnectionString("mongodb+srv://root:root@cluster0.lh5rj.mongodb.net/sample_mflix?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("sample_mflix");
        MongoCollection<Document> movies = database.getCollection("movies");

        List<Movie> list = new ArrayList<>();

        movies.find().limit(6).forEach(d -> list.add(docToMovie(d)));
        ctx.setVariable("list", list);
        mongoClient.close();


        templateEngine.process("index", ctx, response.getWriter());
    }
    public Movie docToMovie(Bson bson) {
        Movie movie = new Movie();
        Document document = (Document) bson;
        movie.set_id(document.getObjectId("_id").toHexString());
        movie.setTitle(MessageFormat.format("{0}", document.get("title")));
        movie.setCast((List<String>) document.get("cast"));
        movie.setPlog(document.getString("plot"));
        movie.setFullPlot(document.getString("fullplot"));
        movie.setType(document.getString("type"));
        movie.setDirectors((List<String>) document.get("directors"));
        movie.setWriters((List<String>) document.get("writers"));
        movie.setCountries((List<String>) document.get("countries"));
        movie.setGenres((List<String>) document.get("genres"));
        movie.setPoster(document.getString("poster"));
        return movie;
    }

}
