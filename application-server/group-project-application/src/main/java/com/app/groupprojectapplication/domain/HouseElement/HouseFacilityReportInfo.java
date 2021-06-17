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
public class HouseFacilityReportInfo {

    private Integer HouseFacilityReportId;
    private String houseFacilityReportTitle;
    private String houseFacilityReportDate;
    private String houseFacilityReportDescription;
    private String  houseFacilityReportStatus;
    private List<ReportDetail> houseFacilityReportDetails;
}
