package org.sergeigroshev.SpringBootSecurityAppTest.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Sergei Y Groshev
 * @version 1.0
 */


@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @ManyToMany
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

}
