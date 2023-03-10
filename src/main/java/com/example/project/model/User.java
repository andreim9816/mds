package com.example.project.model;

import com.example.project.model.security.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

import static com.example.project.model.Regex.CNP_REGEX;
import static com.example.project.model.Regex.NAME_REGEX;

@Getter
@Setter
@Builder
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @NotBlank(message = "Username must be provided!")
    private String username;

    private String password;

    @Pattern(regexp = CNP_REGEX, message = "Invalid CNP!")
    @NotBlank(message = "CNP must be provided!")
    private String cnp;

    @NotBlank(message = "Email must be provided!")
    private String email;

    @Size(min = 3, message = "First name should have minimum 3 letters!")
    @Size(max = 30, message = "First name should have maximum 30 letters!")
    @Pattern(regexp = NAME_REGEX, message = "Invalid first name")
    @NotBlank(message = "First name must be provided!")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(min = 2, message = "Last name should have minimum 2 letters!")
    @Size(max = 30, message = "Last name should have maximum 30 letters!")
    @Pattern(regexp = NAME_REGEX, message = "Invalid last name")
    @NotBlank(message = "Last name must be provided!")
    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "FK_DEPARTMENT_ID")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Consult> consults;

    @OneToOne(mappedBy = "patient")
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;
}


