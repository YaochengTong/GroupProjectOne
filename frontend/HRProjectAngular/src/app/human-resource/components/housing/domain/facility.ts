import { FacilityReport } from './facility-report';

export interface Facility {
  id: number;
  numberOfBeds: number;
  numberOfMattresses: number;
  numberOfTables: number;
  numberOfChairs: number;
  reports: FacilityReport[];
}
