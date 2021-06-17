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
public class HouseFacilityInfo {

    private Integer facilityId;
    private Integer numberOfBeds;
    private Integer numberOfMattresses;
    private Integer numberOfTables;
    private Integer numberOfChairs;
    private List<HouseFacilityReportInfo> houseFacilityReportInfo;

}
