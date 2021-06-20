package com.app.groupprojectapplication.domain.visaStatusManagement;

import lombok.*;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VisaStatusInfo {
    private String fullName;
    private String workAuthorization;
    private String authorizationStartDate;
    private String authorizationEndDate;
    private Integer authorizationDayLeft;
    private List<DocumentInfo> documentReceived;
    private String nextStep;
    private Integer idx;
    private Integer userId;
    private String message;
}
