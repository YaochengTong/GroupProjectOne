import { Component, OnInit, Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-preview',
  templateUrl: './preview.component.html',
  styleUrls: ['./preview.component.scss']
})
export class PreviewComponent implements OnInit {

  path: any;

  constructor(
    public dialogRef: MatDialogRef<PreviewComponent>,
    private sanitizer : DomSanitizer,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.path = data.data;
  }

  getSanitizedURL() {
    return this.sanitizer.bypassSecurityTrustResourceUrl(this.path);
  }

  ngOnInit(): void {
    console.log(this.path)
  }

}
