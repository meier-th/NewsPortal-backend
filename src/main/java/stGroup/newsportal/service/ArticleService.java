package stGroup.newsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stGroup.newsportal.entity.Article;
import stGroup.newsportal.repository.ArticleRepository;

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

}
