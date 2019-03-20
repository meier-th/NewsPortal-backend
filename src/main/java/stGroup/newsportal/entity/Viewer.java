package stGroup.newsportal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "viewer")
public class Viewer {

    private String login;
    private String password;
    private LocalDate signupDate;
    @ManyToMany
    @JoinTable(
            name = "author_subscribtions",
            joinColumns = @JoinColumn(name = "viewer"),
            inverseJoinColumns = @JoinColumn(name = "author")
    )
    private LinkedList<Author> authorSubscriptions;
    @ManyToMany
    @JoinTable(
            name = "theme_subscriptions",
            joinColumns = @JoinColumn(name="viewer"),
            inverseJoinColumns = @JoinColumn(name = "theme")
    )
    private LinkedList<Theme> themeSubscriptions;

}
