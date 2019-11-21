package stGroup.newsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import stGroup.newsportal.entity.Article;
import stGroup.newsportal.entity.User;
import stGroup.newsportal.entity.Theme;
import stGroup.newsportal.repository.ArticleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.Date;
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

    public List<Article> getByAuthor(User user, int pageNum, int number) {
        Pageable pageNeeded = PageRequest.of(pageNum, number);
        List<Article> list = repository.getByAuthor(user, pageNeeded);
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

    public List<Article> getNew(int pageNum, int number) {
         return repository.findAll(PageRequest.of(pageNum, number, Sort.by("dateTime"))).getContent();
                /*PageRequest.of(pageNum, number);
        List<Article> list = repository.getArticlesOrderByDateTime(pageNeeded);
        return list;*/
    }

    public void wipeOldArticles () {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date expiryDate = calendar.getTime();
        repository.deleteOutdatedArticles(expiryDate);
    }

    public void upVoteArticle (Long id) {
        Article article = repository.findById(id).orElse(null);
        if (article == null) {
            throw new EntityNotFoundException();
        } else {
            article.setUpVotes(article.getUpVotes()+1);
            repository.save(article);
        }
    }

    public void downVoteArticle (Long id) {
        Article article = repository.findById(id).orElse(null);
        if (article == null) {
            throw new EntityNotFoundException();
        } else {
            article.setDownVotes(article.getDownVotes()+1);
            repository.save(article);
        }
    }

    public void viewArticle (Long id) {
        Article article = repository.findById(id).orElse(null);
        if (article == null) {
            throw new EntityNotFoundException();
        } else {
            article.setViews(article.getViews()+1);
            repository.save(article);
        }
    }

    public void disDownVote (Long id) {
        Article article = repository.findById(id).orElse(null);
        if (article == null) {
            throw new EntityNotFoundException();
        } else {
            article.setDownVotes(article.getDownVotes()-1);
            repository.save(article);
        }
    }

    public void disUpVote (Long id) {
        Article article = repository.findById(id).orElse(null);
        if (article == null) {
            throw new EntityNotFoundException();
        } else {
            article.setUpVotes(article.getUpVotes()-1);
            repository.save(article);
        }
    }

    public Article getById (Long id) {
        Article article = repository.findById(id).orElse(null);
        return article;
    }

}
