import { FacilityReport } from './facility-report';

export class Facility {
  facilityId: number;
  numberOfBeds: number;
  numberOfMattresses: number;
  numberOfTables: number;
  numberOfChairs: number;
  houseFacilityReportInfo: FacilityReport[];

  constructor(json: any) {
    this.facilityId = json.facilityId;
    this.numberOfBeds = json.numberOfBeds;
    this.numberOfMattresses = json.numberOfMattresses;
    this.numberOfTables = json.numberOfTables;
    this.numberOfChairs = json.numberOfChairs;
    this.houseFacilityReportInfo = json.houseFacilityReportInfo;
  }
}
