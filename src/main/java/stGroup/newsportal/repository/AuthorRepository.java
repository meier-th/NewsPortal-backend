package stGroup.newsportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stGroup.newsportal.entity.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, String> {
}
