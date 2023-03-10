package com.example.project.controller;

import com.example.project.service.DepartmentService;
import com.example.project.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.project.controller.DepartmentController.ALL_DEPARTMENTS;

@Controller
@RequiredArgsConstructor
public class HomeController {

    public final static String ACCESS_DENIED = "access-denied";
    private final static String REGISTER_FORM = "register_form";
    private final static String REGISTER = "register";
    private final static String LOGIN = "login";

    private final PasswordEncoder passwordEncoder;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;

    @GetMapping({"", "/", "/index"})
    public String departmentList(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return ALL_DEPARTMENTS;
    }

//    @GetMapping("/register")
//    public String register(Model model) {
//        if (!model.containsAttribute("user")) {
//            model.addAttribute("user", new MyUser());
//        } else {
//            MyUser user = (MyUser) model.getAttribute("user");
//            user.setPassword("");
//        }
//        if (!model.containsAttribute("doctor")) {
//            model.addAttribute("doctor", new Doctor());
//        }
//        model.addAttribute("password", "");
//        model.addAttribute("departmentAll", departmentService.getAllDepartments());
//
//        return REGISTER_FORM;
//    }

//    @PostMapping("/register")
//    public String processRegister(@ModelAttribute("user") @Valid User user, BindingResult bindingResultUser,
//                                  @ModelAttribute("doctor") @Valid User doctor, BindingResult bindingResultDoctor,
//                                  @ModelAttribute("password") String password, RedirectAttributes attr) {
//        if (bindingResultUser.hasErrors() || bindingResultDoctor.hasErrors()) {
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "user", bindingResultUser);
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "doctor", bindingResultDoctor);
//            attr.addFlashAttribute("user", user);
//            attr.addFlashAttribute("doctor", doctor);
//
//            if (bindingResultUser.getFieldError("password") != null) {
//                attr.addFlashAttribute("error_password", bindingResultUser.getFieldError("password").getDefaultMessage());
//            }
//
//            return REDIRECT + REGISTER;
//        }
//
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        user.setAuthorities(Set.of(authorityService.getByRole(ROLE_DOCTOR)));
//
//        user.setDoctor(doctor);
//        doctor.setUser(user);
//
//        try {
//            doctorService.saveOrUpdateUser(user, doctor, password);
//        } catch (NotUniqueEmailException e) {
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "user", bindingResultUser);
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "doctor", bindingResultDoctor);
//            attr.addFlashAttribute("user", user);
//            attr.addFlashAttribute("doctor", doctor);
//            attr.addFlashAttribute("error_email", e.getMessage());
//            return REDIRECT + REGISTER;
//        } catch (NotUniqueUsernameException e) {
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "user", bindingResultUser);
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "doctor", bindingResultDoctor);
//            attr.addFlashAttribute("user", user);
//            attr.addFlashAttribute("doctor", doctor);
//            attr.addFlashAttribute("error_username", e.getMessage());
//            return REDIRECT + REGISTER;
//        }
//        return REDIRECT + LOGIN;
//    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError() {
        return "login-error";
    }

    @GetMapping("/" + ACCESS_DENIED)
    public String accessDenied() {
        return "err_access_denied";
    }
}
