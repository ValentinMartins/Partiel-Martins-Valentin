package com.example.validation;

import java.time.DayOfWeek;
import java.util.Set;

import com.example.model.Medication;
import com.example.model.Prescription;

public class Rule666 implements ValidationRule {
    private static final int URGENT_STOCK_THRESHOLD = 3;
    private static final double WEEKEND_MARGIN = 1.2;

    @Override
    public boolean isValid(Prescription prescription) {
        Set<Medication> meds = prescription.getMedications();
        boolean isWeekend = prescription.getPrescriptionDay() == DayOfWeek.SATURDAY ||
                            prescription.getPrescriptionDay() == DayOfWeek.SUNDAY;

        for (Medication med : meds) {
            if (med.getName().equals("W")) {
                int requiredStock = isWeekend ? (int) Math.ceil(3 * WEEKEND_MARGIN) : URGENT_STOCK_THRESHOLD;
                if (med.getStockQuantity() < requiredStock) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return "Règle 666 : Stock insuffisant pour le médicament W.";
    }
}
