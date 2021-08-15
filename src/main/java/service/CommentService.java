package service;

import DAO.CommentDAO;
import com.mongodb.client.FindIterable;
import model.Comment;
import org.bson.Document;

public class CommentService {
    public FindIterable<Comment> getComments(String by, Object value) {
        Document filter = new Document();
        Document sort = new Document("date", -1);
        filter.append(by, value);
        FindIterable<Comment> list = new CommentDAO().getComments(filter,sort);
        return list;
    }

    public void addComment(Comment comment) {
        new CommentDAO().addComment(comment);
    }
}
