package br.com.cavalojr.todolist.user;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import br.com.cavalojr.todolist.errors.ConflictException;

@Service // The @Service annotation indicates that this class is a service component in
         // the Spring application context. It is used to define business logic and can
         // be injected into other components, such as controllers or other services.
public class UserService {
    private final IUserRepository userRepository; //
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel create(UserModel userModel) { // This method creates a new user
        var user = this.userRepository.findByEmail(userModel.getEmail());

        if (user != null) {
            System.out.println("Este user name ja foi registrado");
            throw new ConflictException("This username has already been registered.");
        }

        var hashedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(hashedPassword);
        //

        var createdUser = this.userRepository.save(userModel);
        return createdUser;
    }

    public String login(String email, String password) {
        var user = this.userRepository.findByEmail(email);

        if (user == null) {
            throw new ConflictException("User not found.");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ConflictException("Invalid password.");
        }

        // Generate and return a token or session for the authenticated user
        return "VOCE E DOS VERDADEIRO"; // Replace with actual token generation logic
    }
}
