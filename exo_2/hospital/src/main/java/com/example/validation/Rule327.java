package com.example.validation;

import java.time.DayOfWeek;
import java.util.Set;

import com.example.model.Medication;
import com.example.model.Patient;
import com.example.model.Prescription;

public class Rule327 implements ValidationRule {
    @Override
    public boolean isValid(Prescription prescription) {
        Set<Medication> meds = prescription.getMedications();
        Patient patient = prescription.getPatient();
        boolean containsYandZ = meds.stream().anyMatch(m -> m.getName().equals("Y")) &&
                                meds.stream().anyMatch(m -> m.getName().equals("Z"));
        
        boolean hasBRCA1 = patient.getGeneticMarkers().contains("BRCA1");
        boolean isWednesdayWithIRM = prescription.getPrescriptionDay() == DayOfWeek.WEDNESDAY;
        
        return !containsYandZ || hasBRCA1 || isWednesdayWithIRM;
    }

    @Override
    public String getErrorMessage() {
        return "Règle 327 : Interaction médicamenteuse interdite sauf exception BRCA1 ou mercredi sous IRM.";
    }
}

