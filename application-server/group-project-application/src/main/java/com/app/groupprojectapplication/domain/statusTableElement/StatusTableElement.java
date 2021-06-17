package com.app.groupprojectapplication.domain.statusTableElement;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatusTableElement {
    private Integer employeeId;
    private NameInfo nameInfo;
    private VisaInfo visaInfo;
}
