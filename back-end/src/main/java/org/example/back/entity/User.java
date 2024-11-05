package org.example.back.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Canary
 * @version 1.0.0
 * @title User
 * @description <TODO description class purpose>
 * @creat 2024/11/5 下午8:58
 **/
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    @Id
    private String id;
    private String password;
    private String name;
    private byte age;
    private String gender;
    private String department;
}
