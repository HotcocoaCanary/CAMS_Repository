package org.example.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

/**
 * @author Canary
 * @version 1.0.0
 * @creat 2024/11/5 下午4:31
 **/
@AllArgsConstructor
@Entity
@Table(name = "Teachers")
public class Teacher extends User{
    public Teacher(String id, String password, String name, byte age, String gender, String department) {
        super(id, password, name, age, gender, department);
    }
}
