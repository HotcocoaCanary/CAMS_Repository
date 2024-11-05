package org.example.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Canary
 * @version 1.0.0
 * @creat 2024/11/5 下午4:31
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    private String id;
    private String password;
    private String name;
    private byte age;
    private String gender;
    private String department;
}
