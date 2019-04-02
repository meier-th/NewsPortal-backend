package stGroup.newsportal.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import stGroup.newsportal.entity.Article;
import stGroup.newsportal.entity.User;
import stGroup.newsportal.entity.Theme;

import java.util.Date;
import java.util.List;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

    @Query("select a from Article a where a.author = :author order by a.dateTime")
    List<Article> getByAuthor(@Param("user") User user, Pageable pageable);

    @Query("select a from Article a where a.theme = :theme order by a.dateTime")
    List<Article> getByTheme(@Param("theme") Theme theme, Pageable pageable);

    @Query("select a from Article a order by a.upVotes DESC")
    List<Article> getMostUpvoted(Pageable pageable);

    @Query("select a from Article a order by a.views DESC")
    List<Article> getMostViewed(Pageable pageable);

    @Transactional
    default void deleteOutdatedArticles(Date expiryDate) {
        findAll().forEach(article -> {
            if (!article.isImportant() && article.getDateTime().compareTo(expiryDate) <= 0)
                delete(article);
        });
    }

}
