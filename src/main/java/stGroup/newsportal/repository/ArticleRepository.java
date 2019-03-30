package stGroup.newsportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import stGroup.newsportal.entity.Article;
import stGroup.newsportal.entity.Author;
import stGroup.newsportal.entity.Theme;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Query("select a from Article a where a.author = :author")
    List<Article> getAllByAuthor(@Param("author") Author author);

    @Query("select a from Article a where a.theme = :theme")
    List<Article> getAllByTheme(@Param("theme") Theme theme);
}
