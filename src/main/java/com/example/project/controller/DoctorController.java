package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.DepartmentService;
import com.example.project.service.DoctorService;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
@Slf4j
public class DoctorController {

    private final static String ALL_DOCTORS = "doctors";
    private final static String VIEW_DOCTOR = "doctor_info";
    private final static String EDIT_DOCTOR = "doctor_form";

    private final DoctorService doctorService;
    private final DepartmentService departmentService;
    private final UserService userService;

    @GetMapping
    public String getAll(Model model) {
        var doctors = userService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return ALL_DOCTORS;
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") String doctorId, Model model) {
        //TODO check if doctor
        var doctor = userService.findById(Long.valueOf(doctorId));
        model.addAttribute("doctor", doctor);
        return VIEW_DOCTOR;
    }

    @GetMapping("/my-profile")
    public String getMyProfile(Model model) {
        // here is the view accessible only to the doctors where they can see info about themselves and edit data
        User user = userService.getCurrentUser();

        if (!model.containsAttribute("user")) {
            model.addAttribute("user", user);
        } else {
            user = (User) model.getAttribute("user");
            user.setPassword("");
        }
//        if (!model.containsAttribute("doctor")) {
//            model.addAttribute("doctor", doctor);
//        }
        model.addAttribute("departmentAll", departmentService.getAllDepartments());
        model.addAttribute("isDoctor", true);
        model.addAttribute("password", "");

        return EDIT_DOCTOR;
    }

//    @GetMapping("/{id}/edit")
//    public String editDoctor(@PathVariable("id") String doctorId, Model model) {
//        if (userService.isDoctor()) {
//            if (!userService.checkIfCurrentUserIsSameDoctor(Long.valueOf(doctorId))) {
//                throw new ForbiddenException();
//            } else {
//                return REDIRECT + ALL_DOCTORS + "/my-profile";
//            }
//        }
//        // this view is intended only for admins. Doctors can change their role in the "my-profile" section
//        var doctor = userService.findById(Long.valueOf(doctorId));
//        User user = doctor.getUser();
//
//        if (!model.containsAttribute("user")) {
//            model.addAttribute("user", user);
//        } else {
//            user = (User) model.getAttribute("user");
//            user.setPassword("");
//        }
//        if (!model.containsAttribute("doctor")) {
//            model.addAttribute("doctor", doctor);
//        }
//
//        model.addAttribute("password", "");
//        model.addAttribute("departmentAll", departmentService.getAllDepartments());
//        model.addAttribute("isDoctor", false);
//
//        return EDIT_DOCTOR;
//    }

//    @PostMapping
//    public String saveOrUpdate(@ModelAttribute("user") @Valid MyUser user, BindingResult bindingResultUser,
//                               @ModelAttribute("doctor") @Valid Doctor doctor, BindingResult bindingResultDoctor,
//                               @ModelAttribute("password") String password, RedirectAttributes attr) {
//
//        if (bindingResultUser.hasErrors() || bindingResultDoctor.hasErrors()) {
//            log.info("Model binding has errors!");
//
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "user", bindingResultUser);
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "doctor", bindingResultDoctor);
//            attr.addFlashAttribute("user", user);
//            attr.addFlashAttribute("doctor", doctor);
//
//            if (userService.isDoctor()) {
//                return REDIRECT + ALL_DOCTORS + "/my-profile";
//            } else {
//                return REDIRECT + ALL_DOCTORS + "/" + doctor.getId() + "/edit";
//            }
//        }
//
//        user.setAuthorities(Set.of(authorityService.getByRole(ROLE_DOCTOR)));
//        user.setDoctor(doctor);
//        doctor.setUser(user);
//
//        try {
//            doctorService.saveOrUpdateUser(user, doctor, password);
//        } catch (NotUniqueEmailException e) {
//            log.info("Error when saving into database! Error message = " + e.getMessage());
//
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "user", bindingResultUser);
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "doctor", bindingResultDoctor);
//            attr.addFlashAttribute("user", user);
//            attr.addFlashAttribute("doctor", doctor);
//            attr.addFlashAttribute("error_email", e.getMessage());
//            return REDIRECT + ALL_DOCTORS + "/" + doctor.getId() + "/edit";
//        } catch (NotUniqueUsernameException e) {
//            log.info("Error when saving into database! Error message = " + e.getMessage());
//
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "user", bindingResultUser);
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "doctor", bindingResultDoctor);
//            attr.addFlashAttribute("user", user);
//            attr.addFlashAttribute("doctor", doctor);
//            attr.addFlashAttribute("error_username", e.getMessage());
//            return REDIRECT + ALL_DOCTORS + "/" + doctor.getId() + "/edit";
//        }
//        return REDIRECT + ALL_DOCTORS;
//    }

//    @GetMapping("/{id}/delete")
//    public String deleteDoctor(@PathVariable Long id) {
//        doctorService.deleteDoctorById(id);
//        return REDIRECT + ALL_DOCTORS;
//    }
}
