package br.com.cavalojr.todolist.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(UserModel userModel) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userModel.getName());
        userDTO.setUsername(userModel.getUsername());
        userDTO.setPassword(userModel.getPassword());
        return userDTO;
    }

    public UserModel toModel(UserDTO userDTO) {
        UserModel userModel = new UserModel();
        userModel.setName(userDTO.getName());
        userModel.setUsername(userDTO.getUsername());
        userModel.setPassword(userDTO.getPassword());
        return userModel;
    }
}
