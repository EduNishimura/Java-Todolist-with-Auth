package br.com.cavalojr.todolist.user;

import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service // The @Service annotation indicates that this class is a service component in
         // the Spring application context. It is used to define business logic and can
         // be injected into other components, such as controllers or other services.
public class UserService {
    private final IUserRepository userRepository; //

    public UserService(IUserRepository userRepository) { // metodo construtor que recebe uma instancia de
                                                         // IUserRepository como parametro e atribui a variavel
                                                         // userRepository
        this.userRepository = userRepository;
    }

    public UserModel create(UserModel userModel) { // This method creates a new user
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            System.out.println("Este user name ja foi registrado");
            throw new IllegalArgumentException("This username has already been registered");
        }

        var hashedPassword = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(hashedPassword);
        //

        var createdUser = this.userRepository.save(userModel);
        return createdUser;
    }
}
