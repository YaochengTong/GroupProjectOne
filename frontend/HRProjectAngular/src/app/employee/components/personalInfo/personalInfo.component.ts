import { Component, Inject, OnInit } from '@angular/core';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NameSectionDialogComponent } from './name-section-dialog/name-section-dialog.component';
import { AddressSectionDialogComponent } from './address-section-dialog/address-section-dialog.component';
import { ContactSectionDialogComponent } from './contact-section-dialog/contact-section-dialog.component';
import { EmergencyContactSectionDialogComponent } from './emergency-contact-section-dialog/emergency-contact-section-dialog.component';
import { EmploymentSectionDialogComponent } from './employment-section-dialog/employment-section-dialog.component';

@Component({
  selector: 'app-personalInfo',
  templateUrl: './personalInfo.component.html',
  styleUrls: ['./personalInfo.component.scss'],
})
export class PersonalInfoComponent implements OnInit {
  
  public userId!: string;
  public empolyeeId!: number;
  public nameSection: any = {};
  public addressSection: any = {};
  public contactSection: any = {};
  public employmentSection: any = {};
  public emergencyContactList: any = {};
  public documentSection: any = {};
  isDataAvailable: boolean = false;
  

  constructor(
    private httpRequestService: HTTPReq,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.userId = localStorage.getItem("userId")!;
    this.httpRequestService.getData('/profile/'+this.userId, null, 'http://localhost:8999').subscribe(
      (data: any) => {
        console.log(data)
        this.isDataAvailable = true;
        this.empolyeeId = data.profile.employee_id;
        this.nameSection = data.profile.nameSection;
        this.addressSection = data.profile.addressSection;
        this.contactSection = data.profile.contactInfoSection;
        this.employmentSection = data.profile.employmentSection;
        this.emergencyContactList = data.profile.emergencyContactList;
        this.documentSection = data.profile.documentSectionList;
      }
    )
  }

  preview(): void {

  }

  
  editNameSection() :void {
      const dialogRef = this.dialog.open(NameSectionDialogComponent,
        {
          width: '500px',
          data: {"nameSection": this.nameSection}
        });

        dialogRef.afterClosed().subscribe(result => {
          console.log(result);
          // console.log("hello");
          this.httpRequestService.postData('/profile/' + this.userId +"/updateNameSection",
          result,
          'http://localhost:8999').subscribe(
            (data: any) => {
              console.log(data);
            }
          )
        })
  }

  editAddressSection() : void {
    let addVar = {
      priAdd1: this.addressSection.primaryAddr.AddressLine1,
      priAdd2: this.addressSection.primaryAddr.AddressLine2,
      priCity: this.addressSection.primaryAddr.City,
      priState: this.addressSection.primaryAddr.State,
      priZip: this.addressSection.primaryAddr.Zip,
      secAdd1: this.addressSection.secondaryAddr.AddressLine1,
      secAdd2: this.addressSection.secondaryAddr.AddressLine2,
      secCity: this.addressSection.secondaryAddr.City,
      secState: this.addressSection.secondaryAddr.State,
      secZip: this.addressSection.secondaryAddr.Zip,
    }

    const dialogRef = this.dialog.open(AddressSectionDialogComponent,
      {
        width: '500px',
        data: {"addressSection": this.addressSection}
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(addVar);
        this.httpRequestService.postData('/profile/' + this.userId +"/updateAddressSection",
          addVar,
          'http://localhost:8999').subscribe(
            (data: any) => {
              console.log(data);
            }
          )
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
        this.httpRequestService.postData('/profile/' + this.userId +"/updateContactSection",
          result,
          'http://localhost:8999').subscribe(
            (data: any) => {
              console.log(data);
            }
          )
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
        this.httpRequestService.postData('/profile/' + this.userId +"/updateEmploymentSection",
          result,
          'http://localhost:8999').subscribe(
            (data: any) => {
              console.log(data);
            }
          )
      })
  }

  editEmergencyContactList() : void {
    let addVar = {
      EP1fullName: this.emergencyContactList.emergencyPerson1.fullName,
      EP1phone: this.emergencyContactList.emergencyPerson1.phone,
      EP1priAdd1: this.emergencyContactList.emergencyPerson1.address.primaryAddr.AddressLine1,
      EP1priAdd2: this.emergencyContactList.emergencyPerson1.address.primaryAddr.AddressLine2,
      EP1priCity: this.emergencyContactList.emergencyPerson1.address.primaryAddr.City,
      EP1priState: this.emergencyContactList.emergencyPerson1.address.primaryAddr.State,
      EP1priZip: this.emergencyContactList.emergencyPerson1.address.primaryAddr.Zip,
      EP1secAdd1: this.emergencyContactList.emergencyPerson1.address.secondaryAddr.AddressLine1,
      EP1secAdd2: this.emergencyContactList.emergencyPerson1.address.secondaryAddr.AddressLine2,
      EP1secCity: this.emergencyContactList.emergencyPerson1.address.secondaryAddr.City,
      EP1secState: this.emergencyContactList.emergencyPerson1.address.secondaryAddr.State,
      EP1secZip: this.emergencyContactList.emergencyPerson1.address.secondaryAddr.Zip,
      EP2fullName: this.emergencyContactList.emergencyPerson1.fullName,
      EP2phone: this.emergencyContactList.emergencyPerson1.phone,
      EP2priAdd1: this.emergencyContactList.emergencyPerson1.address.primaryAddr.AddressLine1,
      EP2priAdd2: this.emergencyContactList.emergencyPerson1.address.primaryAddr.AddressLine2,
      EP2priCity: this.emergencyContactList.emergencyPerson1.address.primaryAddr.City,
      EP2priState: this.emergencyContactList.emergencyPerson1.address.primaryAddr.State,
      EP2priZip: this.emergencyContactList.emergencyPerson1.address.primaryAddr.Zip,
      EP2secAdd1: this.emergencyContactList.emergencyPerson1.address.secondaryAddr.AddressLine1,
      EP2secAdd2: this.emergencyContactList.emergencyPerson1.address.secondaryAddr.AddressLine2,
      EP2secCity: this.emergencyContactList.emergencyPerson1.address.secondaryAddr.City,
      EP2secState: this.emergencyContactList.emergencyPerson1.address.secondaryAddr.State,
      EP2secZip: this.emergencyContactList.emergencyPerson1.address.secondaryAddr.Zip,
    }
    
    const dialogRef = this.dialog.open(EmergencyContactSectionDialogComponent,
      {
        width: '500px',
        data: {"emergencyContactList": this.emergencyContactList}
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(addVar);
        this.httpRequestService.postData('/profile/' + this.userId +"/updateEmergencySection",
          addVar,
          'http://localhost:8999').subscribe(
            (data: any) => {
              console.log(data);
            }
          )
      })
  }




}
