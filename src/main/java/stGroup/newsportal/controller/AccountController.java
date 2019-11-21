package stGroup.newsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import stGroup.newsportal.config.CredentialsDTO;
import stGroup.newsportal.entity.User;
import stGroup.newsportal.service.UserService;

import java.security.Principal;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping(value = "/registration", produces = "application/json")
    public ResponseEntity<?> signUp(@RequestBody CredentialsDTO dto) {
        try {
            userService.createUser(dto.getUsername(), dto.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    @GetMapping(value="/me")
    public ResponseEntity<String> retrievePrincipal() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
