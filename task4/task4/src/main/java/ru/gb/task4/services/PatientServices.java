package ru.gb.task4.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import ru.gb.task4.models.Patient;

@Service
public class PatientServices {
    List <Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }
    
    public List<Patient> getAllPatients() {
        return patients;
    }

}
