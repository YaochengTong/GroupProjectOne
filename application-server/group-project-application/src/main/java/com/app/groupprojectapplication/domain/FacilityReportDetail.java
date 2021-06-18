package com.app.groupprojectapplication.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "facility_report_detail", schema = "hr_db")
public class FacilityReportDetail implements Serializable {
    private Integer id;
    private String comments;
    private Timestamp createDate;
    private Timestamp lastModificationDate;
    private FacilityReport facilityReport;
    private Employee employee;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "last_modification_date")
    public Timestamp getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Timestamp lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    public FacilityReport getFacilityReport() {
        return facilityReport;
    }

    public void setFacilityReport(FacilityReport facilityReport) {
        this.facilityReport = facilityReport;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacilityReportDetail that = (FacilityReportDetail) o;
        return id == that.id && Objects.equals(comments, that.comments) && Objects.equals(createDate, that.createDate) && Objects.equals(lastModificationDate, that.lastModificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comments, createDate, lastModificationDate);
    }
}
