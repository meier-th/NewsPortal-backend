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
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class User implements Serializable {

    @Id
    private String login;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    private LocalDate signupDate;

    private boolean enabled;

    @ManyToMany(mappedBy = "userSubscriptions")
    private List<User> subscribers;

    private String email;

    @ManyToOne
    @JoinColumn
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "author_subscriptions",
            joinColumns = @JoinColumn(name = "subscriber"),
            inverseJoinColumns = @JoinColumn(name = "author")
    )
    private List<User> userSubscriptions;

    @OneToMany(mappedBy = "author")
    private List<Article> articles;

    private int rating;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ElementCollection
    private Set<Long> upVotedArticles;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ElementCollection
    private Set<Long> downVotedArticles;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ElementCollection
    private Set<Long> upVotedComments;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ElementCollection
    private Set<Long> downVotedComments;

    @ManyToMany
    @JoinTable(
            name = "theme_subscriptions",
            joinColumns = @JoinColumn(name = "subscriber"),
            inverseJoinColumns = @JoinColumn(name = "theme")
    )
    private List<Theme> themeSubscriptions;

    public User() {
        super();
        this.enabled = false;
    }

}