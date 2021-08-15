package utils;

import java.util.Date;

public class MyTime {
    public static String timeAgo(Date currentDate, Date pastDate) {
        long justNow = 10 * 1000; //Milliseconds Per Minute
        long milliSecPerMinute = 60 * 1000; //Milliseconds Per Minute
        long milliSecPerHour = milliSecPerMinute * 60; //Milliseconds Per Hour
        long milliSecPerDay = milliSecPerHour * 24; //Milliseconds Per Day
        long milliSecPerMonth = milliSecPerDay * 30; //Milliseconds Per Month
        long milliSecPerYear = milliSecPerDay * 365; //Milliseconds Per Year
        //Difference in Milliseconds between two dates
        long msExpired = currentDate.getTime() - pastDate.getTime();
        //Second or Seconds ago calculation
        if (msExpired < justNow) {
            return "Just now";
        } else if (msExpired < milliSecPerMinute) {
            return Math.round(msExpired / 1000) + " seconds ago";

        }
        //Minute or Minutes ago calculation
        else if (msExpired < milliSecPerHour) {
            if (Math.round(msExpired / milliSecPerMinute) == 1) {
                return Math.round(msExpired / milliSecPerMinute) + " minute ago";
            } else {
                return Math.round(msExpired / milliSecPerMinute) + " minutes ago";
            }
        }
        //Hour or Hours ago calculation
        else if (msExpired < milliSecPerDay) {
            if (Math.round(msExpired / milliSecPerHour) == 1) {
                return Math.round(msExpired / milliSecPerHour) + " hour ago";
            } else {
                return Math.round(msExpired / milliSecPerHour) + " hours ago";
            }
        }
        //Day or Days ago calculation
        else if (msExpired < milliSecPerMonth) {
            if (Math.round(msExpired / milliSecPerDay) == 1) {
                return Math.round(msExpired / milliSecPerDay) + " day ago";
            } else {
                return Math.round(msExpired / milliSecPerDay) + " days ago";
            }
        }
        //Month or Months ago calculation
        else if (msExpired < milliSecPerYear) {
            if (Math.round(msExpired / milliSecPerMonth) == 1) {
                return Math.round(msExpired / milliSecPerMonth) + "  month ago";
            } else {
                return Math.round(msExpired / milliSecPerMonth) + "  months ago";
            }
        }
        //Year or Years ago calculation
        else {
            if (Math.round(msExpired / milliSecPerYear) == 1) {
                return Math.round(msExpired / milliSecPerYear) + " year ago";
            } else {
                return Math.round(msExpired / milliSecPerYear) + " years ago";
            }
        }
    }
}
