package com.app.groupprojectapplication.domain.visaStatusManagement;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VisaStatusInfo {
    private String fullName;
    private String workAuthorization;
    private Timestamp authorizationStartDate;
    private Timestamp authorizationEndDate;
    private Integer authorizationDayLeft;
    private List<String> documentReceived;
    private String nextStep;
}
