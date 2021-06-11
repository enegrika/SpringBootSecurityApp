package org.sergeigroshev.SpringBootSecurityAppTest.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Sergei Y Groshev
 * @version 1.0
 */

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
