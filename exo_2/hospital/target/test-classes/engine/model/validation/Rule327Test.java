package Partiel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Patient;
import model.Prescription;
import model.Medication;
import java.time.LocalDate;
import java.util.Set;

class Rule327Test {
    @Test
    void testBRCA1Present() {
        Rule327 rule = new Rule327();
        Patient patient = new Patient(2500, false, null, Set.of("BRCA1"));
        Prescription prescription = new Prescription(patient, Set.of(new Medication("Y", 5), new Medication("Z", 5)), LocalDate.now());

        assertTrue(rule.isValid(prescription));
    }

    @Test
    void testInteractionWithoutBRCA1() {
        Rule327 rule = new Rule327();
        Patient patient = new Patient(2500, false, null, Set.of());
        Prescription prescription = new Prescription(patient, Set.of(new Medication("Y", 5), new Medication("Z", 5)), LocalDate.now());

        assertFalse(rule.isValid(prescription));
    }
}
