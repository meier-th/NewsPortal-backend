package stGroup.newsportal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article implements Serializable {

    @Id
    @JsonProperty(access = Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private String heading;
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @ManyToOne
    @JoinColumn(name = "theme")
    private Theme theme;
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;
    private int views;
    private int upVotes;
    private int downVotes;
    private boolean important;

}
