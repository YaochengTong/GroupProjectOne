package com.app.groupprojectapplication.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "facility_report", schema = "hr_db")
public class FacilityReport implements Serializable {
    private int id;
    private String title;
    private Timestamp reportDate;
    private String description;
    private String status;
    private Employee employee;
    private Set<FacilityReportDetail> facilityReportDetailSet;

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
    @Column(name = "report_date")
    public Timestamp getReportDate() {
        return reportDate;
    }

    public void setReportDate(Timestamp reportDate) {
        this.reportDate = reportDate;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "facilityReport")
    public Set<FacilityReportDetail> getFacilityReportDetailSet() {
        return facilityReportDetailSet;
    }

    public void setFacilityReportDetailSet(Set<FacilityReportDetail> facilityReportDetailSet) {
        this.facilityReportDetailSet = facilityReportDetailSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacilityReport that = (FacilityReport) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(reportDate, that.reportDate) && Objects.equals(description, that.description) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, reportDate, description, status);
    }
}
