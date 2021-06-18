package com.app.groupprojectapplication.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    private Integer id;
    private String roleName;
    private String description;
    private Timestamp createDate;
    private Timestamp modificationDate;
    private User user;
    private Set<RolePermission> rolePermissionSet;
    private Set<UserRole> userRoleSet;


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
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    public Set<RolePermission> getRolePermissions() {
        return rolePermissionSet;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissionSet) {
        this.rolePermissionSet = rolePermissionSet;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    public Set<UserRole> getUserRoles() {
        return userRoleSet;
    }

    public void setUserRoles(Set<UserRole> userRoleSet) {
        this.userRoleSet = userRoleSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(roleName, role.roleName) && Objects.equals(description, role.description) && Objects.equals(createDate, role.createDate) && Objects.equals(modificationDate, role.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, description, createDate, modificationDate);
    }
}
