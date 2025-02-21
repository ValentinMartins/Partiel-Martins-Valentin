package com.example.main;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.example.engine.ValidationEngine;
import com.example.model.Medication;
import com.example.model.Patient;
import com.example.model.Prescription;
import com.example.validation.Rule327;
import com.example.validation.Rule666;
import com.example.validation.Rule801;

public class Main {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        DayOfWeek todayDayOfWeek = today.getDayOfWeek();
        LocalDate nextWednesday = today.with(DayOfWeek.WEDNESDAY);

        System.out.println("\n🔬 **Test 1 : Prescription valide**");
        testPrescription(2500, false, null, Set.of("BRCA1"), 5, today);

        System.out.println("\n🚨 **Test 2 : Globules blancs insuffisants (Règle 801)**");
        testPrescription(1900, false, null, Set.of("BRCA1"), 5, today);

        System.out.println("\n✅ **Test 3 : Règle 801 respectée (Protocole Gamma)**");
        testPrescription(1600, true, null, Set.of("BRCA1"), 5, today);

        System.out.println("\n✅ **Test 4 : Règle 801 respectée (Rechute après 2019)**");
        testPrescription(1500, false, LocalDate.of(2021, 5, 10), Set.of("BRCA1"), 5, today);

        System.out.println("\n🚨 **Test 5 : Interaction médicamenteuse (Règle 327 non respectée)**");
        testPrescription(2500, false, null, Set.of(), 5, today);

        System.out.println("\n✅ **Test 6 : Règle 327 respectée (BRCA1 présent)**");
        testPrescription(2500, false, null, Set.of("BRCA1"), 5, today);

        System.out.println("\n✅ **Test 7 : Règle 327 respectée (Mercredi sous IRM)**");
        testPrescription(2500, false, null, Set.of(), 5, nextWednesday);

        System.out.println("\n🚨 **Test 8 : Stock insuffisant en semaine (Règle 666 non respectée)**");
        testPrescription(2500, false, null, Set.of("BRCA1"), 2, today);

        System.out.println("\n🚨 **Test 9 : Stock insuffisant le week-end (Règle 666 non respectée)**");
        LocalDate nextSaturday = today.with(DayOfWeek.SATURDAY);
        testPrescription(2500, false, null, Set.of("BRCA1"), 3, nextSaturday);

        System.out.println("\n✅ **Test 10 : Stock suffisant en semaine (Règle 666 respectée)**");
        testPrescription(2500, false, null, Set.of("BRCA1"), 5, today);

        System.out.println("\n✅ **Test 11 : Stock suffisant le week-end (Règle 666 respectée)**");
        testPrescription(2500, false, null, Set.of("BRCA1"), 4, nextSaturday);
    }

    private static void testPrescription(int whiteBloodCells, boolean isInGamma, LocalDate relapseDate, Set<String> markers, int stockW, LocalDate prescriptionDate) {
        Patient patient = new Patient(whiteBloodCells, isInGamma, relapseDate, markers);
        Medication medX = new Medication("X", 10);
        Medication medY = new Medication("Y", 5);
        Medication medZ = new Medication("Z", 5);
        Medication medW = new Medication("W", stockW);

        Prescription prescription = new Prescription(patient, Set.of(medX, medY, medZ, medW), prescriptionDate);

        ValidationEngine engine = new ValidationEngine(List.of(new Rule801(), new Rule327(), new Rule666()));
        List<String> errors = engine.validate(prescription);

        System.out.println("📋 Prescription pour le " + prescriptionDate);
        if (errors.isEmpty()) {
            System.out.println("✅ Prescription validée !");
        } else {
            System.out.println("⛔ Prescription refusée !");
            errors.forEach(System.out::println);
        }
    }
}
