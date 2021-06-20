package com.app.groupprojectapplication.domain.homeElement;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HomeElement {
    private Integer employeeId;
    private HousingInfo housingInfo;
    private NameInfo nameInfo;
    private VisaInfo visaInfo;
}
