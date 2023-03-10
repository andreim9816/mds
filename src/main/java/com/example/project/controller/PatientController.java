package com.example.project.controller;

import com.example.project.model.dto.PatientDto;
import com.example.project.service.AddressService;
import com.example.project.service.DepartmentService;
import com.example.project.service.PatientService;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.project.controller.DepartmentController.REDIRECT;
import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
@Slf4j
public class PatientController {

    private final static String ALL_PATIENTS = "patients";
    private final static String VIEW_PATIENT = "patient_info";
    private final static String ADD_EDIT_PATIENT = "patient_form";

    private final PatientService patientService;
    private final UserService userService;
    private final DepartmentService departmentService;
    private final AddressService addressService;

    @GetMapping
    public String getAll(Model model) {
        var patients = userService.getAllPatients().stream()
                .map(u -> {
                    PatientDto dto = new PatientDto(u);
                    dto.setAddress(addressService.findByPatientId(u.getId()));
                    return dto;
                })
                .collect(toList());

        model.addAttribute("patients", patients);
        return ALL_PATIENTS;
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") String patientId, Model model) {
        //TODO check if patient
        var patient = userService.findById(Long.valueOf(patientId));
        model.addAttribute("patient", patient);
        var address = addressService.findByPatientId(patient.getId());
        model.addAttribute("address", address);
        return VIEW_PATIENT;
    }

//    @GetMapping("/new")
//    public String addPatient(Model model) {
//        if (!model.containsAttribute("patient")) {
//            model.addAttribute("patient", new Patient());
//        }
//        if (!model.containsAttribute("address")) {
//            model.addAttribute("address", new Address());
//        }
//        model.addAttribute("departmentAll", departmentService.getAllDepartments());
//
//        return ADD_EDIT_PATIENT;
//    }

//    @GetMapping("/{id}/edit")
//    public String editDepartment(@PathVariable("id") String patientId, Model model) {
//        //todo check if patient
//        var patient = userService.findById(Long.valueOf(patientId));
//        var departments = departmentService.getAllDepartments();
//
//        if (!model.containsAttribute("patient")) {
//            model.addAttribute("patient", patient);
//        }
//        if (!model.containsAttribute("address")) {
//            model.addAttribute("address", patient.getAddress());
//        }
//        model.addAttribute("departmentAll", departments);
//
//        return ADD_EDIT_PATIENT;
//    }

//    @PostMapping
//    public String saveOrUpdate(@ModelAttribute("patient") @Valid User patient, BindingResult bindingResultPatient,
//                               @ModelAttribute("address") @Valid Address address, BindingResult bindingResultAddress,
//                               RedirectAttributes attr) {
//        if (bindingResultPatient.hasErrors() || bindingResultAddress.hasErrors()) {
//            log.info("Model binding has errors!");
//
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "patient", bindingResultPatient);
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "address", bindingResultAddress);
//            attr.addFlashAttribute("patient", patient);
//            attr.addFlashAttribute("address", address);
//
//            if (patient.getId() != null) {
//                log.info("Redirected back to endpoint {}/{}/edit", ALL_PATIENTS, patient.getId());
//                return REDIRECT + ALL_PATIENTS + "/" + patient.getId() + "/edit";
//            } else {
//                log.info("Redirected back to endpoint {}/new", ALL_PATIENTS);
//                return REDIRECT + ALL_PATIENTS + "/new";
//            }
//        }
//
//        address.setPatient(patient);
//        patient.setAddress(address);
//
//        try {
//            patientService.savePatient(patient);
////        addressService.saveAddress(address);
//        } catch (CustomException e) {
//            log.info("Error when saving into database! Error message = " + e.getMessage());
//
//            attr.addFlashAttribute(BINDING_RESULT_PATH + "patient", bindingResultPatient);
//            attr.addFlashAttribute("patient", patient);
//            attr.addFlashAttribute("error_cnp", e.getMessage());
//
//            if (patient.getId() == null) {
//                log.info("Redirected back to endpoint {}/new", ALL_PATIENTS);
//                return REDIRECT + ALL_PATIENTS + "/new";
//            } else {
//                log.info("Redirected back to endpoint {}/{}/edit", ALL_PATIENTS, patient.getId());
//                return REDIRECT + ALL_PATIENTS + "/" + patient.getId() + "/edit";
//            }
//        }
//
//        return REDIRECT + ALL_PATIENTS;
//    }

    @GetMapping("/{id}/delete")
    public String deletePatient(@PathVariable Long id) {
        userService.deleteById(id);
        return REDIRECT + ALL_PATIENTS;
    }
}
