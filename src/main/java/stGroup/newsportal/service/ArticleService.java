package stGroup.newsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stGroup.newsportal.entity.Article;
import stGroup.newsportal.entity.Author;
import stGroup.newsportal.entity.Theme;
import stGroup.newsportal.repository.ArticleRepository;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository repository;

    public void addArticle(Article article) {
        repository.save(article);
    }

    public void deleteArticle(Long id) {
        repository.deleteById(id);
    }

    public List<Article> getByAuthor(Author author, int pageNum, int number) {
        Pageable pageNeeded = PageRequest.of(pageNum, number);
        List<Article> list = repository.getByAuthor(author, pageNeeded);
        return list;
    }

    public List<Article> getByTheme(Theme theme, int pageNum, int number) {
        Pageable pageNeeded = PageRequest.of(pageNum, number);
        List<Article> list = repository.getByTheme(theme, pageNeeded);
        return list;
    }

    public List<Article> getByVotes(int pageNum, int number) {
        Pageable pageNeeded = PageRequest.of(pageNum, number);
        List<Article> list = repository.getMostUpvoted(pageNeeded);
        return list;
    }

    public List<Article> getByViews(int pageNum, int number) {
        Pageable pageNeeded = PageRequest.of(pageNum, number);
        List<Article> list = repository.getMostViewed(pageNeeded);
        return list;
    }

}
