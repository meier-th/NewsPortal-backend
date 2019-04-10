package stGroup.newsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stGroup.newsportal.entity.Comment;
import stGroup.newsportal.entity.Theme;
import stGroup.newsportal.service.ArticleService;
import stGroup.newsportal.service.CommentService;
import stGroup.newsportal.service.ThemeService;
import stGroup.newsportal.service.UserService;

@RestController
public class AdminController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ThemeService themeService;

    @GetMapping(value = "/admin/theme", produces = "application/json")
    public ResponseEntity<?> addTheme (@RequestParam String name) {
        try {
            Theme theme = new Theme();
            theme.setName(name);
            themeService.addTheme(theme);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
