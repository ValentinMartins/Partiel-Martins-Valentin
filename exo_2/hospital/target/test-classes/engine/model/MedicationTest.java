package Partiel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MedicationTest {
    @Test
    void testMedicationName() {
        Medication med = new Medication("X", 10);
        assertEquals("X", med.getName());
    }

    @Test
    void testStockQuantity() {
        Medication med = new Medication("X", 10);
        assertEquals(10, med.getStockQuantity());
    }
}