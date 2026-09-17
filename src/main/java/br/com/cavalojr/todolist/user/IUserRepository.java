package br.com.cavalojr.todolist.user;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

// The IUserRepository interface extends the JpaRepository interface, which provides CRUD operations for the UserModel entity.
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
}
