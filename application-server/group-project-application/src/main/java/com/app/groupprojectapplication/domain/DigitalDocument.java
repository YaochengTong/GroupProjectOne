package com.app.groupprojectapplication.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "digital_document", schema = "hr_db")
public class DigitalDocument implements Serializable {
    private Integer id;
    private String type;
    private byte isRequired;
    private String templatePath;
    private String description;

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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "is_required")
    public byte getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(byte isRequired) {
        this.isRequired = isRequired;
    }

    @Basic
    @Column(name = "template_path")
    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalDocument that = (DigitalDocument) o;
        return id == that.id && isRequired == that.isRequired && Objects.equals(type, that.type) && Objects.equals(templatePath, that.templatePath) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, isRequired, templatePath, description);
    }
}
