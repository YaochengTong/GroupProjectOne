import { MaxSizeValidator } from '@angular-material-components/file-input';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ThemePalette } from '@angular/material/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';

export interface NameSectionData {


  nameSection : {
    fullName: string;
    preferredName: string;
    age: number;
    ssn: string;
    dob: string;
    avatar: string;
  } 
}

@Component({
  selector: 'app-name-section-dialog',
  templateUrl: './name-section-dialog.component.html',
  styleUrls: ['./name-section-dialog.component.scss']
})


export class NameSectionDialogComponent implements OnInit {

     //File upload variables
     color: ThemePalette = 'primary';
     multiple: boolean = false;
     avatar!: FormControl;
     files: File | undefined;
     
     public userId!: string;

  constructor(
    private httpRequestServic: HTTPReq,
    public dialogRef: MatDialogRef<NameSectionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data:NameSectionData
  ) { 
    this.avatar = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);
  }

  ngOnInit(): void {
    this.userId = localStorage.getItem('userId')!;
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

  onSave(){
    let formData: FormData = new FormData();
    let params = { userId: this.userId}
    formData.append('file', this.avatar.value);
    this.httpRequestServic.fileUploadWithParams('/profile/'+ this.userId +'/avatar', formData, params).subscribe(
      (data: any) => {
        
        this.data.nameSection.avatar = data.path;
        console.log(data);
      }, 
      err => {
        console.log(err);
      }
    )
    this.dialogRef.close();
  }
  

}