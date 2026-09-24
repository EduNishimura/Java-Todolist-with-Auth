package br.com.cavalojr.todolist.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
