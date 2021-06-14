package com.app.groupprojectapplication.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {
    private int id;
    private String relationship;
    private String title;
    private byte isReferrence;
    private byte isEmergency;
    private byte isLandlord;
    private Person person;
    private Set<House> houseSet;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "relationship")
    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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
    @Column(name = "is_referrence")
    public byte getIsReferrence() {
        return isReferrence;
    }

    public void setIsReferrence(byte isReferrence) {
        this.isReferrence = isReferrence;
    }

    @Basic
    @Column(name = "is_emergency")
    public byte getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(byte isEmergency) {
        this.isEmergency = isEmergency;
    }

    @Basic
    @Column(name = "is_landlord")
    public byte getIsLandlord() {
        return isLandlord;
    }

    public void setIsLandlord(byte isLandlord) {
        this.isLandlord = isLandlord;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="person_id", referencedColumnName = "id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
    public Set<House> getHouses() {
        return houseSet;
    }

    public void setHouses(Set<House> houseSet) {
        this.houseSet = houseSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id && isReferrence == contact.isReferrence && isEmergency == contact.isEmergency && isLandlord == contact.isLandlord && Objects.equals(relationship, contact.relationship) && Objects.equals(title, contact.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, relationship, title, isReferrence, isEmergency, isLandlord);
    }
}
