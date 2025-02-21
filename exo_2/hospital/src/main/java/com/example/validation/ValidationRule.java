package com.example.validation;

import com.example.model.Prescription;

public interface ValidationRule {
    boolean isValid(Prescription prescription);
    String getErrorMessage();
}

