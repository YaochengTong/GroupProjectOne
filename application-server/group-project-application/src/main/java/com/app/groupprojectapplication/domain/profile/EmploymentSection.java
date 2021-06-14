package com.app.groupprojectapplication.domain.profile;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmploymentSection {
    private String workAuthorization;
    private Timestamp authorizationStartDate;
    private Timestamp authorizationEndDate;
    private Timestamp employmentStartDate;
    private Timestamp employmentEndDate;
    private String title;
}
