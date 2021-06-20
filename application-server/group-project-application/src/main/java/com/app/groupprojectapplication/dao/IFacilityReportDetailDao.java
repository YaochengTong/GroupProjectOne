package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.FacilityReport;
import com.app.groupprojectapplication.domain.FacilityReportDetail;

public interface IFacilityReportDetailDao {

    FacilityReportDetail getFacilityReportDetailById(Integer id);
    FacilityReport getFacilityReportByReportDetailId(Integer fId);

    void insertFailicyReportDetail(FacilityReportDetail facilityReportDetail);
}
