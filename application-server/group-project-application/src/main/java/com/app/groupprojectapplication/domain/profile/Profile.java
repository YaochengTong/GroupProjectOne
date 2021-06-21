package com.app.groupprojectapplication.domain.profile;

import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profile {
    private Integer employee_id;
    private NameSection nameSection;
    private AddressSection addressSection;
    private ContactInfoSection contactInfoSection;
    private EmploymentSection employmentSection;
    private EmergencyContactList emergencyContactList;
    private List<DocumentSection> documentSectionList;

}

