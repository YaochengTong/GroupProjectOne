export class FacilityReportDetail {
  reportDetailId: number;
  employeeId: number;
  comments: string;
  createDate: string;


  constructor(json:any) {
    this.reportDetailId = json.reportDetailId;
    this.employeeId = json.employeeId;
    this.comments = json.comments;
    this.createDate = json.createDate;
  }
}
