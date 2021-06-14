package com.app.groupprojectapplication.domain.profile;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NameSection {
    private String fullName;
    private String preferredName;
    private String Avatar;
    private Timestamp DOB;
    private Integer age;
    private String SSN;
}
