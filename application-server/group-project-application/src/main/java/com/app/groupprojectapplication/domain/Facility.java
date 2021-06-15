package com.app.groupprojectapplication.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="facility")
public class Facility implements Serializable {
    private int id;
    private String type;
    private String description;
    private int numberOfBeds;
    private int numberOfMattresses;
    private int numberOfTables;
    private int numberOfChairs;
    private House house;

    public Facility() {
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "number_of_beds")
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    @Basic
    @Column(name = "number_of_mattresses")
    public int getNumberOfMattresses() {
        return numberOfMattresses;
    }

    public void setNumberOfMattresses(int numberOfMattresses) {
        this.numberOfMattresses = numberOfMattresses;
    }

    @Basic
    @Column(name = "number_of_tables")
    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    @Basic
    @Column(name = "number_of_chairs")
    public int getNumberOfChairs() {
        return numberOfChairs;
    }

    public void setNumberOfChairs(int numberOfChairs) {
        this.numberOfChairs = numberOfChairs;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id", referencedColumnName = "id")
    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facility facility = (Facility) o;
        return id == facility.id && numberOfBeds == facility.numberOfBeds && numberOfMattresses == facility.numberOfMattresses && numberOfTables == facility.numberOfTables && numberOfChairs == facility.numberOfChairs && Objects.equals(type, facility.type) && Objects.equals(description, facility.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, description, numberOfBeds, numberOfMattresses, numberOfTables, numberOfChairs);
    }
}
