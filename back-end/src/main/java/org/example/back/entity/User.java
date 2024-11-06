package org.example.back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.back.common.Role;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "cams_repository_db")
public class User {
    @Id
    @Size(max = 50)
    @Column(name = "ID", nullable = false, length = 50)
    private String id;

    @Size(max = 50)
    @NotNull
    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @Size(max = 50)
    @NotNull
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @NotNull
    @ColumnDefault("'男'")
    @Lob
    @Column(name = "Gender", nullable = false)
    private String gender;

    @Size(max = 50)
    @NotNull
    @Column(name = "Department", nullable = false, length = 50)
    private String department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClassID")
    private Class classID;

    @NotNull
    @Lob
    @Column(name = "Role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}