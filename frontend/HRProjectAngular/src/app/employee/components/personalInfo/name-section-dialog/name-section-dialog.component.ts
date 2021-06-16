import { Component, Inject, OnInit } from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

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

  constructor(
    public dialogRef: MatDialogRef<NameSectionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data:NameSectionData
  ) { }

  ngOnInit(): void {
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

}
