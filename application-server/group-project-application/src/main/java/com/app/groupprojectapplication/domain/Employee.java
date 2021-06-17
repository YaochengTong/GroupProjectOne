package com.app.groupprojectapplication.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="employee")
public class Employee implements Serializable {
    private int id;
    private String title;
    private int managerId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String avartar;
    private String car;
    private Timestamp visaStartDate;
    private Timestamp visaEndDate;
    private String driverLicense;
    private Timestamp driverLicenseExpirationDate;
    private House house;
    private Person person;
    private VisaStatus visaStatus;
    private Set<PersonalDocument> personalDocumentSet;
    private Set<FacilityReport> facilityReportSet;
    private Set<FacilityReportDetail> facilityReportDetailSet;
    private Set<RegistrationToken> registrationTokenSet;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "manager_id")
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "avartar")
    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    @Basic
    @Column(name = "car")
    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    @Basic
    @Column(name = "visa_start_date")
    public Timestamp getVisaStartDate() {
        return visaStartDate;
    }

    public void setVisaStartDate(Timestamp visaStartDate) {
        this.visaStartDate = visaStartDate;
    }

    @Basic
    @Column(name = "visa_end_date")
    public Timestamp getVisaEndDate() {
        return visaEndDate;
    }

    public void setVisaEndDate(Timestamp visaEndDate) {
        this.visaEndDate = visaEndDate;
    }

    @Basic
    @Column(name = "driver_license")
    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    @Basic
    @Column(name = "driver_license_expiration_date")
    public Timestamp getDriverLicenseExpirationDate() {
        return driverLicenseExpirationDate;
    }

    public void setDriverLicenseExpirationDate(Timestamp driverLicenseExpirationDate) {
        this.driverLicenseExpirationDate = driverLicenseExpirationDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="house_id", referencedColumnName = "id")
    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="person_id", referencedColumnName = "id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    public Set<PersonalDocument> getPersonalDocumentSet() {
        return personalDocumentSet;
    }

    public void setPersonalDocumentSet(Set<PersonalDocument> personalDocumentSet) {
        this.personalDocumentSet = personalDocumentSet;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visa_status_id", referencedColumnName = "id")
    public VisaStatus getVisaStatus() {
        return visaStatus;
    }

    public void setVisaStatus(VisaStatus visaStatus) {
        this.visaStatus = visaStatus;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    public Set<FacilityReport> getFacilityReportSet() {
        return facilityReportSet;
    }

    public void setFacilityReportSet(Set<FacilityReport> facilityReportSet) {
        this.facilityReportSet = facilityReportSet;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    public Set<FacilityReportDetail> getFacilityReportDetailSet() {
        return facilityReportDetailSet;
    }

    public void setFacilityReportDetailSet(Set<FacilityReportDetail> facilityReportDetailSet) {
        this.facilityReportDetailSet = facilityReportDetailSet;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    public Set<RegistrationToken> getRegistrationTokenSet() {
        return registrationTokenSet;
    }

    public void setRegistrationTokenSet(Set<RegistrationToken> registrationTokenSet) {
        this.registrationTokenSet = registrationTokenSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && managerId == employee.managerId && Objects.equals(title, employee.title) && Objects.equals(startDate, employee.startDate) && Objects.equals(endDate, employee.endDate) && Objects.equals(avartar, employee.avartar) && Objects.equals(car, employee.car) && Objects.equals(visaStartDate, employee.visaStartDate) && Objects.equals(visaEndDate, employee.visaEndDate) && Objects.equals(driverLicense, employee.driverLicense) && Objects.equals(driverLicenseExpirationDate, employee.driverLicenseExpirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, managerId, startDate, endDate, avartar, car, visaStartDate, visaEndDate, driverLicense, driverLicenseExpirationDate);
    }
}
