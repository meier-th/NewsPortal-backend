package stGroup.newsportal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author implements Serializable {
    @Id
    private String login;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    private LocalDate signupDate;
    @ManyToMany(mappedBy = "authorSubscriptions")
    private List<Viewer> subscribers;
    @OneToMany(mappedBy = "author")
    private List<Article> articles;
    private int rating;

}