package com.example.project.repository;

import com.example.project.model.Address;
import com.example.project.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByPatientId(Long id);
}
