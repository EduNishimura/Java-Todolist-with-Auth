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
    private final UserMapper userMapper; // atribute that holds an instance of the UserMapper class, which is
                                         // responsible for mapping between UserDTO and UserModel objects.

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) { // method that handles HTTP POST requests to
                                                                        // create a new user. It receives a UserDTO
                                                                        // object in the request body, which contains
                                                                        // the user data to be created.
        try {
            var userModel = this.userMapper.toModel(userDTO);
            var user = this.userService.create(userModel);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User created: " + this.userMapper.toDTO(user).getUsername());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
