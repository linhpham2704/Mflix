package controller;

import com.mongodb.client.FindIterable;
import model.Comment;
import model.Movie;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import service.CommentService;
import service.MovieService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieController extends MyController{

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        super.process(request, response, servletContext, templateEngine);
        String id = request.getParameter("id");
        Movie movie = new MovieService().getMovieByID(id);
        ctx.setVariable("movie", movie);

        FindIterable<Comment> comments = new CommentService().getComments("movie_id", movie.getId());
        ctx.setVariable("comments", comments);

        templateEngine.process("movie", ctx, response.getWriter());

    }
}
