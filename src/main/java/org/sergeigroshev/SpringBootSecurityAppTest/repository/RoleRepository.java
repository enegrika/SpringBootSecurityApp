package org.sergeigroshev.SpringBootSecurityAppTest.repository;

import org.sergeigroshev.SpringBootSecurityAppTest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sergei Y Groshev
 * @version 1.0
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


}
