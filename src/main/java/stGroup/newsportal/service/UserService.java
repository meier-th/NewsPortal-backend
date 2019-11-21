package stGroup.newsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import stGroup.newsportal.entity.Role;
import stGroup.newsportal.entity.User;
import stGroup.newsportal.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

    public void createUser (String username, String password) throws Exception {
        if (repository.findById(username).orElse(null) != null)
            throw new Exception("Username is already occupied.");
        else {
            User user = new User();
            user.setLogin(username);
            user.setSignupDate(LocalDate.now());
            Role role = new Role();
            role.setName("USER");
            user.setRole(role);
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(password));
            repository.save(user);
        }
    }

}
