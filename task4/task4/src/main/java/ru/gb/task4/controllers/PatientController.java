package ru.gb.task4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import ru.gb.task4.models.Patient;
import ru.gb.task4.services.PatientServices;

@Controller
@AllArgsConstructor
public class PatientController {
    private final PatientServices patientService;
    @GetMapping("/patients")
    public String getPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients";
    }
    @PostMapping("/patients")
    public String addPatient(Patient patient, Model model) {
        patientService.addPatient(patient);
        model.addAttribute("patients");
        return "patients";

    }
}
