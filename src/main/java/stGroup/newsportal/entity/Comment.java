package stGroup.newsportal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "article")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "author")
    private Viewer author;
    private int upVotes;
    private int downVotes;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

}
