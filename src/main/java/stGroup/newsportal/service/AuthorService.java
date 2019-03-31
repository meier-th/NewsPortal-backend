package stGroup.newsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stGroup.newsportal.entity.Author;
import stGroup.newsportal.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public Author getAuthor (String login) {
        Optional<Author> author = repository.findById(login);
        return author.orElse(null);
    }

    public void updateAuthor (Author author) {
        repository.save(author);
    }

    public List<Author> getAllAuthors () {
        List<Author> authors = new ArrayList<>();
        repository.findAll().forEach(authors::add);
        return authors;
    }

}
