package controller;


import model.Comment;
import org.bson.types.ObjectId;
import org.thymeleaf.ITemplateEngine;
import service.CommentService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentController implements IController{

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        if (request.getMethod().equalsIgnoreCase("post") && request.getParameter("action") != null && request.getParameter("action").equals("addComment")) {
            model.Comment comment = new Comment();
            comment.setName(request.getParameter("name"));
            comment.setEmail(request.getParameter("email"));
            comment.setText(request.getParameter("comment"));
            comment.setMovie_id(new ObjectId(request.getParameter("movie_id")));
            new CommentService().addComment(comment);
            response.sendRedirect("/movie?id=" + request.getParameter("movie_id"));
        }
    }
}
