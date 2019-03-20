package stGroup.newsportal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "themes")
public class Theme implements Serializable {
    @Id
    private String name;
    @OneToMany(mappedBy = "theme", fetch = FetchType.LAZY)
    private List<Article> articles;
    @ManyToMany(mappedBy = "themeSubscriptions")
    private List<Viewer> subscribers;
}
