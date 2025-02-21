package Partiel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Patient;
import model.Prescription;
import model.Medication;
import java.time.LocalDate;
import java.util.Set;

class Rule801Test {
    @Test
    void testValidPatient() {
        Rule801 rule = new Rule801();
        Patient patient = new Patient(2500, false, null, Set.of());
        Prescription prescription = new Prescription(patient, Set.of(), LocalDate.now());

        assertTrue(rule.isValid(prescription));
    }

    @Test
    void testPatientWithLowWBC() {
        Rule801 rule = new Rule801();
        Patient patient = new Patient(1500, false, null, Set.of());
        Prescription prescription = new Prescription(patient, Set.of(), LocalDate.now());

        assertFalse(rule.isValid(prescription));
    }
}
