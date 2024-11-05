package org.example.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Canary
 * @version 1.0.0
 * @creat 2024/11/5 下午4:34
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Students")
public class Student {
    @Id
    private String id;
    private String password;
    private String name;
    private byte age;
    private String gender;
    private String department;
    @ManyToOne
    @JoinColumn(name = "ClassID", referencedColumnName = "ClassID")
    private Class classID;
}
