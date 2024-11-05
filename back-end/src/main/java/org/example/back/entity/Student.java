package org.example.back.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

/**
 * @author Canary
 * @version 1.0.0
 * @creat 2024/11/5 下午4:34
 **/
@NoArgsConstructor
@Entity
@Table(name = "Students")
public class Student extends User{
    @ManyToOne
    @JoinColumn(name = "ClassID", referencedColumnName = "ClassID")
    private Class classID;

    public Student(String id, String password, String name, byte age, String gender, String department, Class c) {
        super(id, password, name, age, gender, department);
        this.classID = c;
    }
}
