package stGroup.newsportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stGroup.newsportal.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
