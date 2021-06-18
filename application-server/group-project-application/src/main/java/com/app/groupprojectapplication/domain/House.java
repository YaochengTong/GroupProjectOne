package com.app.groupprojectapplication.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "house")
public class House implements Serializable {
    private Integer id;
    private String address;
    private Integer numberOfPerson;
    private Contact contact;
    private Set<Facility> facilitySet;
    private Set<Employee> employeeSet;

    public House() {
    }

    public House(String address, int numberOfPerson) {
        this.address = address;
        this.numberOfPerson = numberOfPerson;
    }

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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "number_of_person")
    public Integer getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(Integer numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "house")
    public Set<Facility> getFacilitySet() {
        return facilitySet;
    }

    public void setFacilitySet(Set<Facility> facilitySet) {
        this.facilitySet = facilitySet;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "house")
    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return id == house.id && numberOfPerson == house.numberOfPerson && Objects.equals(address, house.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, numberOfPerson);
    }
}
