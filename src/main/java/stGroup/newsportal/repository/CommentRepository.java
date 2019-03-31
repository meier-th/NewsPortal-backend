package stGroup.newsportal.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import stGroup.newsportal.entity.Article;
import stGroup.newsportal.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    @Query("select c from Comment c where c.article = :article order by c.date DESC")
    List<Comment> getComments(@Param("article")Article article, Pageable pageable);

}
