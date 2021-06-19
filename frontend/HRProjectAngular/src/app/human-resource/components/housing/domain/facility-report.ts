import { FacilityReportDetail } from './facility-report-details';

export class FacilityReport {
  houseFacilityReportTitle: string;
  houseFacilityReportDate: string;
  houseFacilityReportDescription: string;
  houseFacilityReportStatus: string;
  houseFacilityReportDetails: FacilityReportDetail[];
  houseFacilityReportId: number;


  constructor(json:any) {
    this.houseFacilityReportTitle = json.houseFacilityReportTitle;
    this.houseFacilityReportDate = json.houseFacilityReportDate;
    this.houseFacilityReportDescription = json.houseFacilityReportDescription;
    this.houseFacilityReportStatus = json.houseFacilityReportStatus;
    this.houseFacilityReportDetails = json.houseFacilityReportDetails;
    this.houseFacilityReportId = json.houseFacilityReportId;
  }
}
