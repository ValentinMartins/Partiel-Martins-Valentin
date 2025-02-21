package com.example.validation;

import com.example.model.Patient;
import com.example.model.Prescription;

public class Rule801 implements ValidationRule {
    @Override
    public boolean isValid(Prescription prescription) {
        Patient patient = prescription.getPatient();
        boolean normalLimit = patient.getWhiteBloodCellCount() > 2000;
        boolean gammaProtocolLimit = patient.isInGammaProtocol() && patient.getWhiteBloodCellCount() > 1500;
        boolean relapseException = patient.hasRelapsedAfter2019();
        return normalLimit || gammaProtocolLimit || relapseException;
    }

    @Override
    public String getErrorMessage() {
        return "Règle 801 : Le patient ne satisfait pas les critères de globules blancs.";
    }
}
