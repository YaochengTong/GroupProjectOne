package com.app.groupprojectapplication.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "facility")
public class Facility implements Serializable {

    private int id;
    private String type;
    private String description;
    private int quantity;
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
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Facility facility = (Facility) o;
        return id == facility.id && quantity == facility.quantity && Objects.equals(type, facility.type) && Objects
            .equals(description, facility.description) && Objects.equals(house, facility.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, description, quantity, house);
    }

    @Override
    public String toString() {
        return "Facility{" + "id=" + id + ", type='" + type + '\'' + ", description='" + description + '\''
            + ", quantity=" + quantity + ", house=" + house + '}';
    }

}
