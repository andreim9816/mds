package com.example.project.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

import static com.example.project.model.Regex.NAME_REGEX;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
    private Long id;

    @NotBlank(message = "Department name must be provided!")
    @Pattern(regexp = NAME_REGEX, message = "Department name is invalid!")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<User> doctors;

    @OneToMany(cascade = CascadeType.ALL)
    @Where(clause = "")
    private List<User> patients;
}
