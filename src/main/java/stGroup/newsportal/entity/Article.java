package stGroup.newsportal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "theme")
    private Theme theme;
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;
    private int views;
    private int upVotes;
    private int downVotes;

}
