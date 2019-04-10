package stGroup.newsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import stGroup.newsportal.entity.Article;
import stGroup.newsportal.entity.User;
import stGroup.newsportal.service.ArticleService;
import stGroup.newsportal.service.CommentService;
import stGroup.newsportal.service.UserService;

@RestController
public class AuthorController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;


    @PostMapping(value = "/create/article", produces = "application/json")
    public ResponseEntity<?> createArticle(@RequestBody Article article, @RequestParam boolean update) {
        try {
            User author = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            if (update) {
                author.getArticles().removeIf(article1 -> article1.getId() == article.getId());
            }
            author.getArticles().add(article);
            userService.updateAuthor(author);
            articleService.addArticle(article);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/article", produces = "application/json")
    public ResponseEntity<?> deleteArticle(@RequestParam long id) {
        try {
            User author = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            Article article = articleService.getById(id);
            if (article == null)
                return ResponseEntity.notFound().build();
            if (!article.getAuthor().equals(author))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            author.getArticles().remove(article);
            articleService.deleteArticle(id);
            return ResponseEntity.ok().build();
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

}
