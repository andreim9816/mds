package com.example.project.service;

import com.example.project.exception.CustomException;
import com.example.project.exception.EntityNotFoundException;
import com.example.project.model.Consult;
import com.example.project.repository.ConsultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultService {

    private final ConsultRepository consultRepository;
    private final UserService userService;

    public Page<Consult> getAllConsults(Pageable pageable) {
        return consultRepository.findAll(pageable);
    }

    public Page<Consult> getConsultsByDoctorId(Pageable pageable, Long doctorId) {
        return consultRepository.getConsultsByDoctorId(doctorId, pageable);
    }

    public List<Consult> getConsultsByDoctorId(Long doctorId) {
        return consultRepository.getConsultsByDoctorId(doctorId);
    }

    public Consult getConsultById(Long id) {
        return consultRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException.builder()
                        .entityId(id)
                        .entityType("Consult")
                        .build()
                );
    }

    public Consult saveConsult(Consult consult) {
        if (consult.getDate().after(new Date())) {
            throw new CustomException("Date cannot be in the future!");
        }

        return consultRepository.save(consult);
    }

    public boolean isMyConsult(Long consultId) {
        Optional<Consult> optionalConsult = consultRepository.findById(consultId);
        if (optionalConsult.isEmpty()) {
            throw EntityNotFoundException.builder()
                    .entityId(consultId)
                    .entityType("Consult")
                    .build();
        }
        return userService.getCurrentUser().getId().equals(optionalConsult.get().getDoctor().getId());
    }

    public void deleteConsultById(Long id) {
        consultRepository.deleteById(id);
    }
}
