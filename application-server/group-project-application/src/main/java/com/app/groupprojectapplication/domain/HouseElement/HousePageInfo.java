package com.app.groupprojectapplication.domain.HouseElement;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HousePageInfo {

    private Integer houseId;
    private String address;
    private String landlord;
    private String phone;
    private Integer numberOfPerson;
    private List<HouseEmployeeInfo> houseEmployeeInfoList;
    private List<HouseFacilityInfo> houseFacilityInfoList;

}
