package com.example.engine;

import java.util.List;

import com.example.model.Prescription;
import com.example.validation.ValidationRule;

import java.util.ArrayList;

public class ValidationEngine {
    private final List<ValidationRule> rules;

    public ValidationEngine(List<ValidationRule> rules) {
        this.rules = rules;
    }

    public List<String> validate(Prescription prescription) {
        List<String> errors = new ArrayList<>();
        for (ValidationRule rule : rules) {
            if (!rule.isValid(prescription)) {
                errors.add(rule.getErrorMessage());
            }
        }
        return errors;
    }
}
