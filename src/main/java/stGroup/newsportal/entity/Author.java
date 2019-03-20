package stGroup.newsportal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author implements Serializable {
    @Id
    @OneToMany(mappedBy = "author")
    @ManyToMany(mappedBy = "authorSubscriptions")
    private String login;
    private String password;
    private LocalDate signupDate;

}