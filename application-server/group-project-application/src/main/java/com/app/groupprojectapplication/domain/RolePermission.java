package com.app.groupprojectapplication.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "role_permission", schema = "hr_db")
public class RolePermission implements Serializable {
    private int id;
    private byte activeFlag;
    private Timestamp createDate;
    private Timestamp modificationDate;
    private User user;
    private Role role;
    private Permission permission;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "active_flag")
    public byte getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(byte activeFlag) {
        this.activeFlag = activeFlag;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="permission_id", referencedColumnName = "id")
    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermission that = (RolePermission) o;
        return id == that.id && activeFlag == that.activeFlag && Objects.equals(createDate, that.createDate) && Objects.equals(modificationDate, that.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activeFlag, createDate, modificationDate);
    }
}
