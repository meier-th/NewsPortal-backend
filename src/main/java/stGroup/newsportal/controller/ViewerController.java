package stGroup.newsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import stGroup.newsportal.entity.Article;
import stGroup.newsportal.entity.Comment;
import stGroup.newsportal.entity.User;
import stGroup.newsportal.service.ArticleService;
import stGroup.newsportal.service.UserService;
import stGroup.newsportal.service.CommentService;
import stGroup.newsportal.service.ThemeService;

import javax.persistence.EntityNotFoundException;

@RestController
public class ViewerController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ThemeService themeService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/articles/new", produces = "application/json")
    public ResponseEntity<?> getNewArticles(@RequestParam int pages, @RequestParam int number) {
            try {
                return ResponseEntity.ok(articleService.getNew(pages, number));
            } catch (Throwable error) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
            }
    }

    @GetMapping(value = "/articles/popular", produces = "application/json")
    public ResponseEntity<?> getPopularArticles(@RequestParam int pages, @RequestParam int number) {
        try {
            return ResponseEntity.ok(articleService.getByViews(pages, number));
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @GetMapping(value = "/articles/upVoted", produces = "application/json")
    public ResponseEntity<?> getMostUpVotedArticles(@RequestParam int pages, @RequestParam int number) {
        try {
            return ResponseEntity.ok(articleService.getByVotes(pages, number));
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @GetMapping(value = "/articles/{theme}", produces = "application/json")
    public ResponseEntity<?> getByTheme(@PathVariable String theme, @RequestParam int pages, @RequestParam int number) {
        try {
            return ResponseEntity.ok(articleService.getByTheme(themeService.getThemeByName(theme), pages, number));
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @GetMapping(value= "/articles/{author}", produces = "application/json")
    public ResponseEntity<?> getByAuthor(@PathVariable String author, int pages, int number) {
        try {
            return ResponseEntity.ok(articleService.getByAuthor(userService.getUser(author), pages, number));
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @PostMapping(value = "/comment", produces = "application/json")
    public ResponseEntity<?> writeComment(@RequestBody Comment comment) {
        try {
            commentService.addComment(comment);
            return ResponseEntity.ok().build();
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @GetMapping(value = "/upvote/article", produces = "application/json")
    public ResponseEntity<?> upVoteArticle(@RequestParam long articleId) {
        try {
            User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            if (user.getUpVotedArticles().contains(articleId)) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("The Article is already upvoted");
            }
            if (user.getDownVotedArticles().contains(articleId)) {
                user.getDownVotedArticles().remove(articleId);
                articleService.disDownVote(articleId);
            }
            user.getUpVotedArticles().add(articleId);
            articleService.upVoteArticle(articleId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/downvote/article", produces = "application/json")
    public ResponseEntity<?> downVoteArticle(@RequestParam long articleId) {
        try {
            User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            if (user.getDownVotedArticles().contains(articleId)) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("The article is already downvoted");
            }
            if (user.getUpVotedArticles().contains(articleId)) {
                user.getUpVotedArticles().remove(articleId);
                articleService.disUpVote(articleId);
            }
            user.getDownVotedArticles().add(articleId);
            articleService.upVoteArticle(articleId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/view", produces = "application/json")
    public ResponseEntity<?> viewArticle(@RequestParam long articleId) {
        try {
            articleService.viewArticle(articleId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/upvote/comment", produces = "application/json")
    public ResponseEntity<?> upVoteComment (@RequestParam long id) {
        try {
            User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            if (user.getUpVotedComments().contains(id))
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("The comment is already upvoted");
            if (user.getDownVotedComments().contains(id)) {
                user.getDownVotedComments().remove(id);
                commentService.disDownVoteComment(id);
            }
            commentService.upVoteComment(id);
            user.getUpVotedComments().add(id);
            userService.updateAuthor(user);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/downvote/comment", produces = "application/json")
    public ResponseEntity<?> downVoteComment (@RequestParam long id) {
        try {
            User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            if (user.getDownVotedComments().contains(id))
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("The comment is already downvoted");
            if (user.getUpVotedComments().contains(id)) {
                user.getUpVotedComments().remove(id);
                userService.updateAuthor(user);
                commentService.disUpVoteComment(id);
            }
            commentService.downVoteComment(id);
            user.getDownVotedComments().add(id);
            userService.updateAuthor(user);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/disdownvote/article", produces = "application/json")
    public ResponseEntity<?> disDownVoteArticle (@RequestParam long id) {
        try {
            User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            if (user.getDownVotedArticles().contains(id)) {
                user.getDownVotedArticles().remove(id);
                userService.updateAuthor(user);
                articleService.disDownVote(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("This article was not downvoted");
            }
        } catch (EntityNotFoundException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/disupvote/article", produces = "application/json")
    public ResponseEntity<?> disUpVoteArticle (@RequestParam long id) {
        try {
            User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            if (user.getUpVotedArticles().contains(id)) {
                user.getUpVotedArticles().remove(id);
                userService.updateAuthor(user);
                articleService.disUpVote(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("This article was not upvoted");
            }
        } catch (EntityNotFoundException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/disdownvote/comment", produces = "application/json")
    public ResponseEntity<?> disDownVoteComment (@RequestParam long id) {
        try {
            User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            if (user.getDownVotedComments().contains(id)) {
                user.getDownVotedComments().remove(id);
                userService.updateAuthor(user);
                commentService.disDownVoteComment(id);
                return ResponseEntity.ok().build();
            } else
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("This comment was not downvoted");
        } catch (EntityNotFoundException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/disupvote/comment", produces = "application/json")
    public ResponseEntity<?> disUpVoteComment (@RequestParam long id) {
        try {
            User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            if (user.getUpVotedArticles().contains(id)) {
                user.getUpVotedArticles().remove(id);
                userService.updateAuthor(user);
                commentService.disUpVoteComment(id);
                return ResponseEntity.ok().build();
            } else
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        } catch (EntityNotFoundException error) {
            return ResponseEntity.notFound().build();
        }
    }

}
