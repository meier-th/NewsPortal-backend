package stGroup.newsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import stGroup.newsportal.config.CredentialsDTO;
import stGroup.newsportal.service.UserService;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/registration", produces = "application/json")
    public ResponseEntity<?> signUp(@RequestBody CredentialsDTO dto) {
        try {
            userService.createUser(dto.getUsername(), dto.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username "+dto.getUsername()+" is already occupied!");
        }
    }

}
