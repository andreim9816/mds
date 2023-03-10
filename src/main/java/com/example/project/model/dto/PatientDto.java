package com.example.project.model.dto;

import com.example.project.model.Address;
import com.example.project.model.Consult;
import com.example.project.model.Department;
import com.example.project.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class PatientDto {

    public PatientDto(User patient) {
        this.id = patient.getId();
        this.username = patient.getUsername();
        this.cnp = patient.getCnp();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.department = patient.getDepartment();
        this.consults = patient.getConsults();
    }

    private Long id;

    @NotBlank(message = "Username must be provided!")
    private String username;

    private String cnp;

    private String email;

    private String firstName;

    private String lastName;

    private Department department;

    private List<Consult> consults;

    private Address address;
}
