package stream.news.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date date = new Date();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User author;

    @Column(nullable = false)
    private String link;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Post() {
    }

    public Post(Integer id, String title, String content, User author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.link = link;
    }

    @Override
    public String toString() {
        return "Post []";
    }
}
