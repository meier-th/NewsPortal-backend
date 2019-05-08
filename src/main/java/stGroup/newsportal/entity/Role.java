package stGroup.newsportal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Role {

    @Id
    private String name;

    @OneToMany(mappedBy = "role")
    List<User> users;

}
