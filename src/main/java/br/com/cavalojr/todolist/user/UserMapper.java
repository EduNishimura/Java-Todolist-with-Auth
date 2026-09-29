package br.com.cavalojr.todolist.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(UserModel userModel) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userModel.getName());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setPassword(userModel.getPassword());
        return userDTO;
    }

    public UserModel toModel(UserDTO userDTO) {
        UserModel userModel = new UserModel();
        userModel.setName(userDTO.getName());
        userModel.setEmail(userDTO.getEmail());
        userModel.setPassword(userDTO.getPassword());
        return userModel;
    }

    public UserResponseDTO toResponseDTO(UserModel userModel) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(userModel.getName());
        userResponseDTO.setEmail(userModel.getEmail());
        return userResponseDTO;
    }
}
