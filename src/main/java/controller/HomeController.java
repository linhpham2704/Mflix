package controller;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import model.Movie;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import service.MovieService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class HomeController extends MyController {

    public void process(final HttpServletRequest request, final HttpServletResponse response, final ServletContext servletContext, final ITemplateEngine templateEngine) throws Exception {
        super.process(request, response, servletContext, templateEngine);
        String by = null;
        String value = null;
        String text = null;
        String url = "/?";
        if (request.getParameter("by") != null) {
            by = request.getParameter("by").trim();
            url = url + "&by=" + by;
        }
        if (request.getParameter("value") != null) {
            value = request.getParameter("value").trim();
            url = url + "&value=" + value;
        }
        if (request.getParameter("text") != null) {
            text = request.getParameter("text").trim();
            url = url + "&text=" + text;
        }
        ctx.setVariable("url", url);

        boolean showCarousel = true;
        boolean showBreadcrumb = true;
        if (by != null || text != null) {  //Filter
            showCarousel = false;
            if (by != null)
                ctx.setVariable("breadCrumb", value);
            else if (text != null)
                ctx.setVariable("breadCrumb", "Search result for: <b>" + text + "</b>");
        } else { //Home
            showBreadcrumb = false;
        }
        ctx.setVariable("showCarousel", showCarousel);
        ctx.setVariable("showBreadcrumb", showBreadcrumb);

        long totalPages = new MovieService().getTotalPages(by, value, text);
        ctx.setVariable("totalPages", totalPages);
        int page = 1;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page").trim());
        ctx.setVariable("page", page);


        List<Movie> list = new MovieService().searchMovies(by, value, page, text);
        ctx.setVariable("list", list);
        templateEngine.process("index", ctx, response.getWriter());
    }

}
