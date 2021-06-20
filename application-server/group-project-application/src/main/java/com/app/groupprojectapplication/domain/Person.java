package com.app.groupprojectapplication.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="person")
public class Person implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String primaryPhone;
    private String alternatePhone;
    private String gender;
    private String ssn;
    private Timestamp dob;
    private Set<User> userSet;
    private Set<Address> addressSet;
    private Set<Contact> contactSet;

    public Person(Integer id, String firstName, String lastName, String middleName, String email, String primaryPhone, String alternatePhone, String gender, String ssn, Timestamp dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.primaryPhone = primaryPhone;
        this.alternatePhone = alternatePhone;
        this.gender = gender;
        this.ssn = ssn;
        this.dob = dob;
    }

    public Person() {

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
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "primary_phone")
    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    @Basic
    @Column(name = "alternate_phone")
    public String getAlternatePhone() {
        return alternatePhone;
    }

    public void setAlternatePhone(String alternatePhone) {
        this.alternatePhone = alternatePhone;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "SSN")
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Basic
    @Column(name = "DOB")
    public Timestamp getDob() {
        return dob;
    }

    public void setDob(Timestamp dob) {
        this.dob = dob;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    public Set<User> getUsers() {
        return userSet;
    }

    public void setUsers(Set<User> userSet) {
        this.userSet = userSet;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    public Set<Address> getAddresses() {
        return addressSet;
    }

    public void setAddresses(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    public Set<Contact> getContacts() {
        return contactSet;
    }

    public void setContacts(Set<Contact> contactSet) {
        this.contactSet = contactSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(middleName, person.middleName) && Objects.equals(email, person.email) && Objects.equals(primaryPhone, person.primaryPhone) && Objects.equals(alternatePhone, person.alternatePhone) && Objects.equals(gender, person.gender) && Objects.equals(ssn, person.ssn) && Objects.equals(dob, person.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, middleName, email, primaryPhone, alternatePhone, gender, ssn, dob);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", primaryPhone='" + primaryPhone + '\'' +
                ", alternatePhone='" + alternatePhone + '\'' +
                ", gender='" + gender + '\'' +
                ", ssn='" + ssn + '\'' +
                ", dob=" + dob +
                '}';
    }
}