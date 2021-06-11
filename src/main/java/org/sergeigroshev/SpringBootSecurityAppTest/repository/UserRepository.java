package org.sergeigroshev.SpringBootSecurityAppTest.repository;

import org.sergeigroshev.SpringBootSecurityAppTest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sergei Y Groshev
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
