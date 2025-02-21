package Partiel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Set;

class PrescriptionTest {
    @Test
    void testPrescriptionCreation() {
        Patient patient = new Patient(2500, false, null, Set.of("BRCA1"));
        Medication med = new Medication("X", 10);
        Prescription prescription = new Prescription(patient, Set.of(med), LocalDate.now());

        assertNotNull(prescription.getPatient());
        assertFalse(prescription.getMedications().isEmpty());
    }
}
