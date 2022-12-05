package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser,HIPAACompliantAdmin {
    private String role;
    private ArrayList<String> securityIncidents = new ArrayList<String>();

    public AdminUser(Integer id,String role) {
        super(id);
    }
    // TO DO: Implement HIPAACompliantUser!
    // TO DO: Implement HIPAACompliantAdmin!
    @Override
    public boolean assignPin(int pin) {
        String intStr = Integer.toString(pin);
        int length = intStr.length();
        boolean returnVal = false;
        if (length == 6 ){
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
        else{
            authIncident();
        }
        return returnVal;
    }


    @Override
    public ArrayList<String> reportSecurityIncidents() {
        return securityIncidents;
    }

    public void newIncident(String notes) {
        String report = String.format(
                "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n",
                new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
                "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n",
                new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<String> getSecurityIncidents() {
        return securityIncidents;
    }

    public void setSecurityIncidents(ArrayList<String> securityIncidents) {
        this.securityIncidents = securityIncidents;
    }
}
