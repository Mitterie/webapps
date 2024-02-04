package modele.POJO;

import java.time.LocalDate;
import java.time.LocalTime;

public class Video {
    
    private String url;
    private String title;
    private LocalDate dayOut;
    private LocalTime timeOut;
    
    public Video(String url, String title, LocalDate dayOut, LocalTime timeOut) {
        this.url = url;
        this.title = title;
        this.dayOut = dayOut;
        this.timeOut = timeOut;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDayOut() {
        return dayOut;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

}
