package br.com.cavalojr.todolist.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService; // atribute that holds an instance of the UserService class, which is
                                           // responsible for handling user-related operations.

    public UserController(UserService userService) { // constructor that receives an instance of the UserService class
                                                     // as a parameter and assigns it to the userService attribute. This
                                                     // allows the controller to use the methods defined in the service
                                                     // for handling user-related requests.
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody UserModel userModel) {
        try {
            var user = this.userService.create(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
