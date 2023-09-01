package com.hackathon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Builder

@NoArgsConstructor
@Getter
@Entity
public class Stats {
    @Id
    @Column(nullable = false, unique = false)
    private int registerCount;
    @Column(nullable = false, unique = false)
    private int onlinePassCount;
    @Column(nullable = false, unique = false)
    private int offlinePassCount;
    @Column(nullable = false, unique = false)
    private int hackerthonCount;

    public int getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(int registerCount) {
        this.registerCount = registerCount;
    }

    public int getOnlinePassCount() {
        return onlinePassCount;
    }

    public void setOnlinePassCount(int onlinePassCount) {
        this.onlinePassCount = onlinePassCount;
    }

    public int getOfflinePassCount() {
        return offlinePassCount;
    }

    public void setOfflinePassCount(int offlinePassCount) {
        this.offlinePassCount = offlinePassCount;
    }

    public int getHackerthonCount() {
        return hackerthonCount;
    }

    public void setHackerthonCount(int hackerthonCount) {
        this.hackerthonCount = hackerthonCount;
    }

    public Stats(int registerCount, int onlinePassCount, int offlinePassCount, int hackerthonCount) {
        this.registerCount = registerCount;
        this.onlinePassCount = onlinePassCount;
        this.offlinePassCount = offlinePassCount;
        this.hackerthonCount = hackerthonCount;
    }
}
