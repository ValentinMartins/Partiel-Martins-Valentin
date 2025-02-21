package com.example.model;

import java.time.LocalDate;
import java.util.Set;

public class Patient {
    private final int whiteBloodCellCount;
    private final boolean isInGammaProtocol;
    private final boolean hasRelapsedAfter2019;
    private final Set<String> geneticMarkers;
    private final LocalDate lastRelapseDate;

    public Patient(int whiteBloodCellCount, boolean isInGammaProtocol, LocalDate lastRelapseDate, Set<String> geneticMarkers) {
        this.whiteBloodCellCount = whiteBloodCellCount;
        this.isInGammaProtocol = isInGammaProtocol;
        this.lastRelapseDate = lastRelapseDate;
        this.hasRelapsedAfter2019 = lastRelapseDate != null && lastRelapseDate.isAfter(LocalDate.of(2019, 12, 31));
        this.geneticMarkers = geneticMarkers;
    }

    public int getWhiteBloodCellCount() { return whiteBloodCellCount; }
    public boolean isInGammaProtocol() { return isInGammaProtocol; }
    public boolean hasRelapsedAfter2019() { return hasRelapsedAfter2019; }
    public Set<String> getGeneticMarkers() { return geneticMarkers; }
}
