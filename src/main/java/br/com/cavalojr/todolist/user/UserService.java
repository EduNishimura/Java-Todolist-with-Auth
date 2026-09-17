package br.com.cavalojr.todolist.user;

import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service // The @Service annotation indicates that this class is a service component in
         // the Spring application context. It is used to define business logic and can
         // be injected into other components, such as controllers or other services.
public class UserService {
    private final IUserRepository userRepository; //

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository; // UserService is a service class that handles user-related operations. It
                                              // has a dependency on IUserRepository, which is injected through the
                                              // constructor. This allows the service to access the methods defined in
                                              // the repository for performing CRUD operations on users.
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
        return this.userRepository.save(userModel);
    }
}
