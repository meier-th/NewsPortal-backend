package stGroup.newsportal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "viewer")
public class Viewer implements Serializable {

    @Id
    private String login;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    private LocalDate signupDate;
    @ManyToMany
    @JoinTable(
            name = "author_subscribtions",
            joinColumns = @JoinColumn(name = "viewer"),
            inverseJoinColumns = @JoinColumn(name = "author")
    )
    private List<Author> authorSubscriptions;
    @ManyToMany
    @JoinTable(
            name = "theme_subscriptions",
            joinColumns = @JoinColumn(name="viewer"),
            inverseJoinColumns = @JoinColumn(name = "theme")
    )
    private List<Theme> themeSubscriptions;

}
