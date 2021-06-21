import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-preview',
  templateUrl: './preview.component.html',
  styleUrls: ['./preview.component.scss']
})
export class PreviewComponent implements OnInit {
  userId: number;
  file:string;
  trustedUrl: SafeUrl;

  constructor(
    public dialogRef: MatDialogRef<PreviewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private sanitizer: DomSanitizer
  ) {
    this.userId = data.userId;
    this.file = data.file;
    console.log(this.userId);
    console.log(this.file);
    this.trustedUrl = sanitizer.bypassSecurityTrustUrl("https://gp1storage.s3.us-east-2.amazonaws.com/"+ this.userId +"/"+this.file+".txt");

  }

  ngOnInit(): void {}

}
