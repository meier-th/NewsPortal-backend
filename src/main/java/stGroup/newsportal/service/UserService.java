package stGroup.newsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stGroup.newsportal.entity.User;
import stGroup.newsportal.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User getUser (String login) {
        Optional<User> user = repository.findById(login);
        return user.orElse(null);
    }

    public void updateAuthor (User user) {
        repository.save(user);
    }

    public List<User> getAllAuthors () {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }

}
