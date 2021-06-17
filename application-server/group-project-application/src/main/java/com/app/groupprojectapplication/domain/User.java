package com.app.groupprojectapplication.domain;

import com.amazonaws.services.opsworks.model.App;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity()
@Table(name="user")
public class User implements Serializable {
    private int id;
    private String username;
    private String email;
    private String password;
    private Timestamp createDate;
    private Timestamp modificationDate;
    private Set<Permission> permissionSet;
    private Set<RolePermission> rolePermissionSet;
    private Set<Role> roleSet;
    private Set<UserRole> userRoleSet;
    private Person person;
    private Set<PersonalDocument> personalDocumentSet;
    private Set<ApplicationWorkflow> applicationWorkflowSet;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Permission> getPermissions() {
        return permissionSet;
    }

    public void setPermissions(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<RolePermission> getRolePermissions() {
        return rolePermissionSet;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissionSet) {
        this.rolePermissionSet = rolePermissionSet;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Role> getRoles() {
        return roleSet;
    }

    public void setRoles(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<UserRole> getUserRoles() {
        return userRoleSet;
    }

    public void setUserRoles(Set<UserRole> userRoleSet) {
        this.userRoleSet = userRoleSet;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="person_id", referencedColumnName = "id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<PersonalDocument> getPersonalDocumentSet() {
        return personalDocumentSet;
    }

    public void setPersonalDocumentSet(Set<PersonalDocument> personalDocumentSet) {
        this.personalDocumentSet = personalDocumentSet;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<ApplicationWorkflow> getApplicationWorkflowSet() {
        return applicationWorkflowSet;
    }

    public void setApplicationWorkflowSet(Set<ApplicationWorkflow> applicationWorkflowSet) {
        this.applicationWorkflowSet = applicationWorkflowSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(createDate, user.createDate) && Objects.equals(modificationDate, user.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, createDate, modificationDate);
    }
}
