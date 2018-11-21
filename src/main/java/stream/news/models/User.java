package stream.news.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

// Needed for JPA (Java Persistence API)


/**
 * Annotate the Entity Classes: User and Post
 * Put JPA annotations (table and column mappings + relationship mappings) to the entity classes in order to make then ready
 * for persistence in the database through the JPA / Hibernate technology
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30, unique = true)
    @NotEmpty(message = "User Name field cannot be left blank!")
    private String userName;

    @Column(length = 30)
    private String role = "USER";

    @Column(length = 60)
    @NotEmpty(message = "Password field cannot be left blank!")
    @Length(min = 5, message = "Your password must have at least 5 characters")
    private String passwordHash;

    @Column(length = 100)
    @NotEmpty(message = "Full Name field cannot be left blank!")
    private String fullName;

    @OneToMany(mappedBy = "author")
    private Set<Post> posts = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(Integer id, String userName, String fullName) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", passwordHash=" + passwordHash + ", fullName=" + fullName + "]";
    }


}
