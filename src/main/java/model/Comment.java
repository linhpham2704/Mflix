package model;

import lombok.Data;
import org.bson.types.ObjectId;
import utils.MyTime;

import java.util.Date;

@Data
public class Comment {
    private ObjectId id;
    private String name;
    private String email;
    private String text;
    private ObjectId movie_id;
    private Date date;

    public String getTimeAgo() {
        return MyTime.timeAgo(new Date(), date);
    }
}
