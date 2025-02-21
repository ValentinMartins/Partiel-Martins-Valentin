package Partiel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Set;

class PatientTest {
    @Test
    void testHasRelapsedAfter2019() {
        Patient patient = new Patient(2500, false, LocalDate.of(2021, 5, 10), Set.of("BRCA1"));
        assertTrue(patient.hasRelapsedAfter2019());

        Patient patient2 = new Patient(2500, false, LocalDate.of(2018, 5, 10), Set.of("BRCA1"));
        assertFalse(patient2.hasRelapsedAfter2019());
    }
}
