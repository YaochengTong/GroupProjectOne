import { Employee } from './employee';
import { Facility } from './facility';

export interface House {
  id: number;
  address: string;
  landlord: string;
  phone: string;
  numberOfPerson: number;
  employee: Employee[];
  facility: Facility[];
}
