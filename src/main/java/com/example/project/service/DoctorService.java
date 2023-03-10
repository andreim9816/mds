package com.example.project.service;

import com.example.project.exception.EntityNotFoundException;
import com.example.project.model.Consult;
import com.example.project.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

//    private final ConsultService consultService;
//    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;
//
//    public List<User> getAllDoctors() {
//        return userService.getAllDoctors();
//    }
//
//    public List<Consult> getAllConsultsForDoctor(Long doctorId) {
//        return consultService.getConsultsByDoctorId(doctorId);
//    }
//
//    public Doctor getById(Long id) {
//        return doctorRepository.findById(id)
//                .orElseThrow(() -> EntityNotFoundException.builder()
//                        .entityId(id)
//                        .entityType("Doctor")
//                        .build());
//    }

//    public MyUser saveOrUpdateUser(MyUser user, Doctor doctor, String password) {
//
//        var userUsername = userService.loadUserByUsername(user.getUsername());
//        var userEmail = userService.getUserByEmail(user.getEmail());
//
//        if (userUsername.isPresent() && !Objects.equals(userUsername.get().getIdentifier(), user.getIdentifier())) {
//            throw new NotUniqueUsernameException(String.format("Username %s already exists!", user.getUsername()));
//        }
//
//        if (userEmail.isPresent() && !Objects.equals(userEmail.get().getEmail(), user.getEmail())) {
//            throw new NotUniqueEmailException(String.format("Email %s already exists!", user.getEmail()));
//        }
//
//        doctor.setConsults(getAllConsultsForDoctor(doctor.getId()));
//
//        if (!isLoggedIn()) {
//             register form
//            doctor.setUser(user);
//            user.setDoctor(doctor);
//            return saveDoctor(doctor).getUser();
//
//        }
//        if (userService.isAdmin()) {
//             admins can't change passwords
//            var doctorInDB = getById(user.getDoctor().getId());
//            var userInDB = doctorInDB.getUser();
//
//            doctorInDB.setFirstName(doctor.getFirstName());
//            doctorInDB.setLastName(doctor.getLastName());
//            doctorInDB.setDepartment(doctor.getDepartment());

//            userInDB.setUsername(user.getUsername());
//            userInDB.setEmail(user.getEmail());
//
//            doctorInDB.setUser(userInDB);
//            userInDB.setDoctor(doctorInDB);
//
//            return saveDoctor(doctorInDB).getUser();
//
//        } else {
            // only doctors can change their own passwords
//            if ("".equals(password)) {
//                 password not provided. then dont change it
//                var currentPassword = userService.getCurrentUser().getPassword();
//                user.setPassword(currentPassword);
//            } else {
                // password provided. change it
//                var encodedPassword = passwordEncoder.encode(password);
//                user.setPassword(encodedPassword);
//            }
//
//            var doctorInDB = getById(user.getDoctor().getId());
//            var userInDB = doctorInDB.getUser();
//
//            doctorInDB.setFirstName(doctor.getFirstName());
//            doctorInDB.setLastName(doctor.getLastName());
//            doctorInDB.setDepartment(doctor.getDepartment());
//
//            userInDB.setEmail(user.getEmail());
//            userInDB.setPassword(user.getPassword());
//
//            doctorInDB.setUser(userInDB);
//            userInDB.setDoctor(doctorInDB);
//
//
//            return saveDoctor(doctorInDB).getUser();
//        }
//    }

//    public Doctor saveDoctor(Doctor doctor) {
//        return doctorRepository.save(doctor);
//    }

//    public void deleteDoctorById(Long id) {
//        doctorRepository.deleteById(id);
//    }
}
