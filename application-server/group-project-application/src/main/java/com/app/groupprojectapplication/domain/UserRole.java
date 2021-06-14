package com.app.groupprojectapplication.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_role", schema = "hr_db")
public class UserRole implements Serializable {
    private int id;
    private byte activateFlag;
    private Timestamp createDate;
    private Timestamp modificationDate;
    private User user;
    private Role role;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "activate_flag")
    public byte getActivateFlag() {
        return activateFlag;
    }

    public void setActivateFlag(byte activateFlag) {
        this.activateFlag = activateFlag;
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
    @Column(name = "modification_date")
    public Timestamp getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Timestamp modificationDate) {
        this.modificationDate = modificationDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="last_modification_user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id", referencedColumnName = "id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return id == userRole.id && activateFlag == userRole.activateFlag && Objects.equals(createDate, userRole.createDate) && Objects.equals(modificationDate, userRole.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activateFlag, createDate, modificationDate);
    }
}
