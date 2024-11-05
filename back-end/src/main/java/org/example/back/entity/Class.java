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
@Table(name = "Classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classID;
    private String className;
    @ManyToOne
    @JoinColumn(name = "ClassTeacher", referencedColumnName = "ID")
    private Teacher classTeacher;
    private String department;
}
