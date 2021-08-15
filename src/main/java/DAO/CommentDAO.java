package DAO;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import model.Comment;
import org.bson.Document;

import java.util.Date;

public class CommentDAO extends AbsDAO{
    public FindIterable<model.Comment> getComments(Document filter, Document sort) {
        MongoCollection<model.Comment> comments = getDB().getCollection("comments", model.Comment.class);
        FindIterable<model.Comment> list = comments.find(filter).sort(sort);
        return list;
    }

    public void addComment(model.Comment comment) {
        MongoCollection<model.Comment> comments = getDB().getCollection("comments", Comment.class);
        comment.setDate(new Date());
        comments.insertOne(comment);
    }
}
