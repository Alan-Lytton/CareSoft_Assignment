package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class Physician extends User implements HIPAACompliantUser {

    private ArrayList<String> patientNotes;

    public Physician(Integer id) {
        super(id);
    }

    // TO DO: Implement HIPAACompliantUser!
    @Override
    public boolean assignPin(int pin) {
        String intStr = Integer.toString(pin);
        int length = intStr.length();
        boolean returnVal = false;
        if (length == 4 ){
            this.setPin(pin);
            returnVal = true;
        }
        return returnVal;
    }


    @Override
    public boolean accessAuthorized(Integer confirmedAuthID) {
        boolean returnVal = false;
        if (this.getId().equals(confirmedAuthID)){
            returnVal = true;
        }
        return returnVal;
    }

    public void newPatientNotes(String notes, String patientName, Date date) {
        String report = String.format(
                "Datetime Submitted: %s \n", date);
        report += String.format("Reported By ID: %s\n", this.id);
        report += String.format("Patient Name: %s\n", patientName);
        report += String.format("Notes: %s \n", notes);
        this.patientNotes.add(report);
    }

    public ArrayList<String> getPatientNotes() {
        return patientNotes;
    }

    public void setPatientNotes(ArrayList<String> patientNotes) {
        this.patientNotes = patientNotes;
    }


}
