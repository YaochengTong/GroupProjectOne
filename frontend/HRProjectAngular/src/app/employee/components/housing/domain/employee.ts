export class Employee {
  employeeId: number;
  employeeName: string;
  employeePhone: string;
  employeeEmail: string;
  employeeCar: string;

  constructor(json: any) {
    this.employeeId = json.employeeId;
    this.employeeName = json.employeeName;
    this.employeePhone = json.employeePhone;
    this.employeeEmail = json.employeeEmail;
    this.employeeCar = json.employeeCar;
  }
}
