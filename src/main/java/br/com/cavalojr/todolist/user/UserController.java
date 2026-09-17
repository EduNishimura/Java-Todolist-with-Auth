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

    private UserService userService; // UserService is a service class that handles user-related operations. It has a
                                     // dependency on IUserRepository, which is injected through the constructor.
                                     // This allows the service to access the methods defined in the repository for
                                     // performing CRUD operations on users.

    public UserController(UserService userService) {
        this.userService = userService; // UserController is a REST controller that handles HTTP requests related to
                                        // users. It has a dependency on UserService, which is injected through the
                                        // constructor. This allows the controller to access the methods defined in the
                                        // service for performing user-related operations.
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
