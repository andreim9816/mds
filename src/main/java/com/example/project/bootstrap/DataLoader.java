package com.example.project.bootstrap;

import com.example.project.model.Address;
import com.example.project.model.Department;
import com.example.project.model.User;
import com.example.project.model.security.Role;
import com.example.project.repository.AddressRepository;
import com.example.project.repository.DepartmentRepository;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static com.example.project.configuration.SecurityConfiguration.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        loadUserData();
    }

    @Transactional
    void loadUserData() {
        if (userRepository.count() == 0) {

            Role adminRole = Role.builder()
                    .name(ROLE_ADMIN)
                    .build();

            Role doctorRole = Role.builder()
                    .name(ROLE_DOCTOR)
                    .build();

            Role pacientRole = Role.builder()
                    .name(ROLE_PACIENT)
                    .build();

            Department department1 = new Department();
            department1.setName("Cardiologie");

            Department department2 = new Department();
            department2.setName("Neurologie");

            User userAdmin = User.builder()
                    .cnp("1000000000000")
                    .username("admin_1")
                    .password(passwordEncoder.encode("123456"))
                    .email("admin_1@email.com")
                    .firstName("Admin")
                    .lastName("Admin")
                    .roles(Collections.singleton(adminRole))
                    .build();

            User userDoctor1 = User.builder()
                    .cnp("1111111111111")
                    .username("doctor_1")
                    .password(passwordEncoder.encode("123456"))
                    .email("doctor_1@email.com")
                    .firstName("Doctor")
                    .lastName("Unu")
                    .department(department1)
                    .roles(Collections.singleton(doctorRole))
                    .build();

            User userDoctor2 = User.builder()
                    .cnp("2222222222222")
                    .username("doctor_2")
                    .password(passwordEncoder.encode("123456"))
                    .email("doctor_2@email.com")
                    .firstName("Doctor")
                    .lastName("Doi")
                    .department(department1)
                    .roles(Collections.singleton(doctorRole))
                    .build();

            User userDoctor3 = User.builder()
                    .cnp("3333333333333")
                    .username("doctor_3")
                    .password(passwordEncoder.encode("123456"))
                    .email("doctor_3@email.com")
                    .firstName("Doctor")
                    .lastName("Trei")
                    .department(department2)
                    .roles(Collections.singleton(doctorRole))
                    .build();

            Address address = Address.builder()
                    .city("Roman")
                    .no(2)
                    .street("Tineretului")
                    .build();

            User userPacient1 = User.builder()
                    .cnp("4444444444444")
                    .username("pacient_1")
                    .password(passwordEncoder.encode("123456"))
                    .email("pacient_1@email.com")
                    .firstName("Pacient")
                    .lastName("Unu")
                    .department(department1)
                    .address(address)
                    .roles(Collections.singleton(pacientRole))
                    .build();

            address.setPatient(userPacient1);

            department1.setDoctors(List.of(userDoctor1, userDoctor2));
            department2.setDoctors(List.of(userDoctor3));
            department2.setPatients(List.of(userPacient1));

            entityManager.persist(adminRole);
            entityManager.persist(doctorRole);
            entityManager.persist(pacientRole);

            userRepository.save(userAdmin);

            userRepository.save(userDoctor1);
            userRepository.save(userDoctor2);
            userRepository.save(userDoctor3);

            userRepository.save(userPacient1);

            departmentRepository.save(department1);
            departmentRepository.save(department2);
            addressRepository.save(address);
        }
    }
}
