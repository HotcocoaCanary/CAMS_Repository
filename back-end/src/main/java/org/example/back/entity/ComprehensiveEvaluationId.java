package org.example.back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.back.common.Term;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serial;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ComprehensiveEvaluationId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 3443463218373598782L;
    @Size(max = 50)
    @NotNull
    @Column(name = "StudentID", nullable = false, length = 50)
    private String studentID;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'FRESHMAN_FALL'")
    @Lob
    @Column(name = "Term", nullable = false)
    private Term term;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ComprehensiveEvaluationId entity = (ComprehensiveEvaluationId) o;
        return Objects.equals(this.studentID, entity.studentID) && Objects.equals(this.term, entity.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, term);
    }

}