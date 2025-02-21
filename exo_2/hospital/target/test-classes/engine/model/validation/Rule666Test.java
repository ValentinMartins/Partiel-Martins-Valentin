package Partiel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Patient;
import model.Prescription;
import model.Medication;
import java.time.LocalDate;
import java.util.Set;

class Rule666Test {
    @Test
    void testStockSufficient() {
        Rule666 rule = new Rule666();
        Prescription prescription = new Prescription(new Patient(2500, false, null, Set.of()), Set.of(new Medication("W", 5)), LocalDate.now());

        assertTrue(rule.isValid(prescription));
    }

    @Test
    void testStockInsufficient() {
        Rule666 rule = new Rule666();
        Prescription prescription = new Prescription(new Patient(2500, false, null, Set.of()), Set.of(new Medication("W", 2)), LocalDate.now());

        assertFalse(rule.isValid(prescription));
    }
}
