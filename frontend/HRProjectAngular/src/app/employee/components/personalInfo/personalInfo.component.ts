import { Component, OnInit } from '@angular/core';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';

export interface Tile {
  color: string;
  cols: number;
  rows: number;
  text: string;
}

@Component({
  selector: 'app-personalInfo',
  templateUrl: './personalInfo.component.html',
  styleUrls: ['./personalInfo.component.scss'],
})
export class PersonalInfoComponent implements OnInit {
  tiles: Tile[] = [
    { text: 'Name', cols: 2, rows: 2, color: 'lightblue' },
    { text: 'Address', cols: 2, rows: 2, color: 'lightgreen' },
    { text: 'Contact Info', cols: 2, rows: 2, color: 'lightpink' },
    { text: 'Employment', cols: 2, rows: 2, color: '#DDBDF1' },
    { text: 'Emergency Contact', cols: 2, rows: 2, color: '#DDBDF1' },
    { text: 'Document Section', cols: 2, rows: 2, color: '#DDBDF1' },
  ];
  public userId!: string;
  public empolyeeId!: number;
  public nameSection;
  public addressSection;
  public contactSection;
  public employmentSection;
  public emergencyContactList;
  

  constructor(
    private httpRequestService: HTTPReq
  ) {}

  ngOnInit(): void {
    this.userId = localStorage.getItem("userId")!;
    this.httpRequestService.getData('/profile/'+this.userId, null, 'http://localhost:8999').subscribe(
      (data: any) => {
        this.empolyeeId = data.profile.employee_id;
        this.nameSection = data.profile.nameSection;
        this.addressSection = data.profile.addressSection;
        this.contactSection = data.profile.contactInfoSection;
        this.employmentSection = data.profile.employmentSection;
        this.emergencyContactList = data.profile.emergencyContactList;
        console.log(this.addressSection);
        console.log(this.nameSection.fullName);
        console.log(this.emergencyContactList[0]);
      }
    )
  }

}
