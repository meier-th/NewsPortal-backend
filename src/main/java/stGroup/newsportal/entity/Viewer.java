package stGroup.newsportal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

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
    private List<Author> authorSubscriptions;
    @ManyToMany
    private List<Theme> themeSubscriptions;

}
