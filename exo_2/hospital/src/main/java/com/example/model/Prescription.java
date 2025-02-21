package com.example.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class Prescription {
    private final Patient patient;
    private final Set<Medication> medications;
    private final LocalDate prescriptionDate;

    public Prescription(Patient patient, Set<Medication> medications, LocalDate prescriptionDate) {
        this.patient = patient;
        this.medications = medications;
        this.prescriptionDate = prescriptionDate;
    }

    public Patient getPatient() { return patient; }
    public Set<Medication> getMedications() { return medications; }
    public DayOfWeek getPrescriptionDay() { return prescriptionDate.getDayOfWeek(); }
}
