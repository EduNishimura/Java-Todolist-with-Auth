package br.com.cavalojr.todolist.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cavalojr.todolist.user.UserService;
import jakarta.validation.Valid;
import br.com.cavalojr.todolist.user.UserDTO;
import br.com.cavalojr.todolist.user.UserMapper;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;

    public AuthController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        var userModel = this.userMapper.toModel(userDTO);
        var user = this.userService.create(userModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.userMapper.toResponseDTO(user));
    }

    @PostMapping("/login")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

}
