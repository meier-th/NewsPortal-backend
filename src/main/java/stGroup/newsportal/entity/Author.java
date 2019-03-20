package stGroup.newsportal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author implements Serializable {
    @Id
    private String login;
    private String password;
    private LocalDate signupDate;
    @ManyToMany(mappedBy = "authorSubscriptions")
    private LinkedList<Viewer> subscribers;
    @OneToMany(mappedBy = "author")
    private ArrayList<Article> articles;
    private int rating;

}