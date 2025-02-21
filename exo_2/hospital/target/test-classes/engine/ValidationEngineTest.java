package Partiel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Patient;
import model.Prescription;
import model.Medication;
import validation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

class ValidationEngineTest {
    @Test
    void testValidationSuccess() {
        ValidationEngine engine = new ValidationEngine(List.of(new Rule801(), new Rule327(), new Rule666()));
        Patient patient = new Patient(2500, false, null, Set.of("BRCA1"));
        Prescription prescription = new Prescription(patient, Set.of(new Medication("X", 10)), LocalDate.now());

        assertTrue(engine.validate(prescription).isEmpty());
    }
}
