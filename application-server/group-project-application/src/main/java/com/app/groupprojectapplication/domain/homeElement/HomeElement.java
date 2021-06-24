package com.app.groupprojectapplication.domain.homeElement;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class HomeElement {
    private Integer employeeId;
    private HousingInfo housingInfo;
    private NameInfo nameInfo;
    private VisaInfo visaInfo;
}
