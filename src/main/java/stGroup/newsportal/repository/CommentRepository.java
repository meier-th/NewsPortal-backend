package stGroup.newsportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stGroup.newsportal.entity.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
