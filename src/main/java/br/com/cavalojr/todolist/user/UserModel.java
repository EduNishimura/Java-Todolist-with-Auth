package br.com.cavalojr.todolist.user;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

// jakarta.persistence package provides annotations for defining the mapping between Java classes and database tables.
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// Model: class that represents the data structure of a user in the application.
// Is annotated with @Entity to indicate that it is a JPA entity and will be mapped to a database table named "tb_users".

@Data // The @Data annotation from Lombok generates boilerplate code such as getters,
      // setters, equals, hashCode, and toString methods.
@Entity(name = "tb_users") // The @Entity annotation specifies that this class is a JPA entity and will be
                           // mapped to a database table named "tb_users".
public class UserModel {

    @Id // The @Id annotation indicates that the id field is the primary key of the
        // entity.
    @GeneratedValue(generator = "UUID") // The @GeneratedValue annotation specifies that the id field will be
                                        // automatically generated using a UUID generator.
    private UUID id;

    @Column(nullable = false, unique = true) // The @Column annotation is used to specify the mapping of the username
                                             // field to a database column.
    private String username;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
