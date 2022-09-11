package com.example.cs487hw1_1;

import java.sql.Timestamp;

public class RecordModel {

    public String getPsu_id() {
        return psu_id;
    }

    public void setPsu_id(String psu_id) {
        this.psu_id = psu_id;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Timestamp entry_time) {
        this.entry_time = entry_time;
    }

    private String psu_id;
    private int status;
    private String role;
    private Timestamp entry_time;

    public RecordModel(String psu_id, int status, String role, Timestamp entry_time) {
        this.psu_id = psu_id;
        this.status = status;
        this.role = role;
        this.entry_time = entry_time;
    }

}
