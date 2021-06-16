export interface House {
  address: string;
  landlord: string;
  phone: string;
  numberOfPerson: number;
  employee: Employee[];
  facility: Facility[];
}

export interface Employee {
  name: string;
  phone: string;
  email: string;
  car: string;
}

export interface Facility {
  numberOfBeds: number;
  numberOfMattresses: number;
  numberOfTables:number;
  numberOfChairs:number;
  reports:string;
}
