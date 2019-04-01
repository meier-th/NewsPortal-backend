package stGroup.newsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stGroup.newsportal.service.ArticleService;
import stGroup.newsportal.service.AuthorService;
import stGroup.newsportal.service.ThemeService;

@RestController
public class ViewerController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ThemeService themeService;
    @Autowired
    private AuthorService authorService;

    @GetMapping(value = "/new", produces = "application/json")
    public ResponseEntity<?> getNewArticles(@RequestParam int pages, @RequestParam int number) {
            try {
                return ResponseEntity.ok(articleService.getNew(pages, number));
            } catch (Throwable error) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
            }
    }

    @GetMapping(value = "/popular", produces = "application/json")
    public ResponseEntity<?> getPopularArticles(@RequestParam int pages, @RequestParam int number) {
        try {
            return ResponseEntity.ok(articleService.getByViews(pages, number));
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @GetMapping(value = "/upVoted", produces = "application/json")
    public ResponseEntity<?> getMostUpVotedArticles(@RequestParam int pages, @RequestParam int number) {
        try {
            return ResponseEntity.ok(articleService.getByVotes(pages, number));
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @GetMapping(value = "/{theme}", produces = "application/json")
    public ResponseEntity<?> getByTheme(@PathVariable String theme, @RequestParam int pages, @RequestParam int number) {
        try {
            return ResponseEntity.ok(articleService.getByTheme(themeService.getThemeByName(theme), pages, number));
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

    @GetMapping(value= "/{author}", produces = "application/json")
    public ResponseEntity<?> getByAuthor(@PathVariable String author, int pages, int number) {
        try {
            return ResponseEntity.ok(articleService.getByAuthor(authorService.getAuthor(author), pages, number));
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }

}
