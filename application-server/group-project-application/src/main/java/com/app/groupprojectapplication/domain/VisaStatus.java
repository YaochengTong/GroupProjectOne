package com.app.groupprojectapplication.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "visa_status", schema = "hr_db")
public class VisaStatus {
    private int id;
    private String visaType;
    private byte isActive;
    private Timestamp modificationDate;
    private Set<Employee> employeeSet;
    private User user;

    public VisaStatus() {
    }

    public VisaStatus(String visaType, byte isActive, Timestamp modificationDate) {
        this.visaType = visaType;
        this.isActive = isActive;
        this.modificationDate = modificationDate;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "visa_type")
    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    @Basic
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "modification_date")
    public Timestamp getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Timestamp modificationDate) {
        this.modificationDate = modificationDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "visaStatus")
    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creation_user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisaStatus that = (VisaStatus) o;
        return id == that.id && isActive == that.isActive && Objects.equals(visaType, that.visaType) && Objects.equals(modificationDate, that.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, visaType, isActive, modificationDate);
    }

    @Override
    public String toString() {
        return "VisaStatus{" +
                "id=" + id +
                ", visaType='" + visaType + '\'' +
                ", isActive=" + isActive +
                ", modificationDate=" + modificationDate +
                '}';
    }
}