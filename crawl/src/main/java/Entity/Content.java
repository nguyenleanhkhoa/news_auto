package Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "CONTENT")
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

//    @Column(name = "news_id")
//    private long newsId;


    @Column(name = "content")
    private String content;

    @Column(name = "category_content")
    private String categoryContent;

    @Column(name = "time_content",nullable = false)
    private String timeContent;

    @Column(name = "time_created")
    private Date timeCreated;

    @Column(name = "time_updated")
    private Date timeUpdated;

//    @OneToOne
//    @JoinColumn(name = "news_id")
//    private News news;
@ManyToOne
@JoinColumn(name="news_id", nullable=false,insertable = false, updatable = false)
private News news;



    public Content() {
    }

    public Content(long newsId, String content, String categoryContent , String timeContent, Date timeCreated, Date timeUpdated, News news) {
//        this.newsId = newsId;
        this.content = content;
        this.timeContent = timeContent;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.categoryContent = categoryContent;
        this.news = news;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryContent() {
        return categoryContent;
    }

    public void setCategoryContent(String categoryContent) {
        this.categoryContent = categoryContent;
    }

    public String getTimeContent() {
        return timeContent;
    }

    public void setTimeContent(String timeContent) {
        this.timeContent = timeContent;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }


}
