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
public class User implements Serializable {
    @Id

    private String login;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    private LocalDate signupDate;

    @ManyToMany(mappedBy = "userSubscriptions")
    private List<User> subscribers;

    @ManyToMany
    @JoinTable(
            name = "author_subscribtions",
            joinColumns = @JoinColumn(name = "subscriber"),
            inverseJoinColumns = @JoinColumn(name = "author")
    )
    private List<User> userSubscriptions;

    @OneToMany(mappedBy = "user")
    private List<Article> articles;

    private int rating;

    @ManyToMany
    @JoinTable(
            name = "upvote_article",
            joinColumns = @JoinColumn(name = "upvoted_article"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Article> upVotedArticles;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToMany
    @JoinTable(
            name = "downvote_article",
            joinColumns = @JoinColumn(name = "downvoted_article"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    private List<Article> downVotedArticles;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToMany
    @JoinTable(
            name = "upvote_comment",
            joinColumns = @JoinColumn(name = "upvoted_comment"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    private List<Comment> upVotedComments;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToMany
    @JoinTable(
            name = "downvote_comment",
            joinColumns = @JoinColumn(name = "downvoted_comment"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    private List<Comment> downVotedComments;

    @ManyToMany
    @JoinTable(
            name = "theme_subscriptions",
            joinColumns = @JoinColumn(name = "subscriber"),
            inverseJoinColumns = @JoinColumn(name = "theme")
    )
    private List<Theme> themeSubscriptions;

}