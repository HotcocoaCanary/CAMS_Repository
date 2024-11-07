package org.example.back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @Size(max = 50)
    @Column(name = "TeacherID", nullable = false, length = 50)
    private String teacherID;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TeacherID", nullable = false)
    private User users;

    @Size(max = 50)
    @NotNull
    @Column(name = "Department", nullable = false, length = 50)
    private String department;

}