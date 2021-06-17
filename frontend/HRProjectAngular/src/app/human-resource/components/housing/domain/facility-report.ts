import { FacilityReportDetail } from './facility-report-details';

export interface FacilityReport {
  id: number;
  title: string;
  reportDate: string;
  description: string;
  status: string;
  details: FacilityReportDetail[];
}
