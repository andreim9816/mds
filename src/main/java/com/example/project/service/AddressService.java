package com.example.project.service;

import com.example.project.exception.EntityNotFoundException;
import com.example.project.model.Address;
import com.example.project.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException.builder().entityId(id).entityType("Address").build());
    }

    public Address findByPatientId(Long patientId) {
        return addressRepository.findByPatientId(patientId);
    }
}
