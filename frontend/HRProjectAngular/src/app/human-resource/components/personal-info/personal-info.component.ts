import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NameSectionDialogComponent } from './name-section-dialog/name-section-dialog.component';
import { AddressSectionDialogComponent } from './address-section-dialog/address-section-dialog.component';
import { ContactSectionDialogComponent } from './contact-section-dialog/contact-section-dialog.component';
import { EmergencyContactSectionDialogComponent } from './emergency-contact-section-dialog/emergency-contact-section-dialog.component';
import { EmploymentSectionDialogComponent } from './employment-section-dialog/employment-section-dialog.component';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';

@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.scss'],
})
export class PersonalInfoComponent implements OnInit {
  public userId!: string;
  public empolyeeId!: number;
  public nameSection;
  public addressSection;
  public contactSection;
  public employmentSection;
  public emergencyContactList;
  

  constructor(
    private httpRequestService: HTTPReq,
    public dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    console.log("Userid" + this.userId);
    this.userId = localStorage.getItem("userId")!;
    this.httpRequestService.getData('/profile/'+this.userId, null, 'http://localhost:8999').subscribe(
      (data: any) => {
        this.empolyeeId = data.profile.employee_id;
        this.nameSection = data.profile.nameSection;
        this.addressSection = data.profile.addressSection;
        this.contactSection = data.profile.contactInfoSection;
        this.employmentSection = data.profile.employmentSection;
        this.emergencyContactList = data.profile.emergencyContactList;
      }
    )
  }

  editNameSection() :void {
      const dialogRef = this.dialog.open(NameSectionDialogComponent,
        {
          width: '500px',
          data: {"nameSection": this.nameSection}
        });

        dialogRef.afterClosed().subscribe(result => {
          console.log(result);
          this.httpRequestService.postData('/profile/' + this.userId +"/update",
          result,
          'http://localhost:8999').subscribe(
            (data: any) => {
              console.log(data);
            }
          )
        })
  }

  editAddressSection() : void {
    const dialogRef = this.dialog.open(AddressSectionDialogComponent,
      {
        width: '500px',
        data: {"addressSection": this.addressSection}
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
      })
  }

  editContactSection() : void {
    const dialogRef = this.dialog.open(ContactSectionDialogComponent,
      {
        width: '500px',
        data: {"contactSection": this.contactSection}
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
      })
  }
  

  editEmploymentSection() : void {
    const dialogRef = this.dialog.open(EmploymentSectionDialogComponent,
      {
        width: '500px',
        data: {"employmentSection": this.employmentSection}
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
      })
  }

  editEmergencyContactList() : void {
    const dialogRef = this.dialog.open(EmergencyContactSectionDialogComponent,
      {
        width: '500px',
        data: {"emergencyContactList": this.emergencyContactList}
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
      })
  }


}
