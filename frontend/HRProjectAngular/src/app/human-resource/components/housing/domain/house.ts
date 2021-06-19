import { Employee } from './employee';
import { Facility } from './facility';

export class House {
  houseId: number;
  address: string;
  landlord: string;
  phone: string;
  numberOfPerson: number;
  houseEmployeeInfoList: Employee[];
  houseFacilityInfoList: Facility[];

  constructor(json: any) {
    this.houseId = json.houseId;
    this.address = json.address;
    this.landlord = json.landlord;
    this.phone = json.phone;
    this.numberOfPerson = json.numberOfPerson;
    this.houseEmployeeInfoList = json.houseEmployeeInfoList;
    this.houseFacilityInfoList = json.houseFacilityInfoList;
  }
}
