import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-preview-txt',
  templateUrl: './preview-txt.component.html',
  styleUrls: ['./preview-txt.component.scss'],
})
export class PreviewTxtComponent implements OnInit {
  userId: number;

  constructor(
    public dialogRef: MatDialogRef<PreviewTxtComponent>,
    @Inject(MAT_DIALOG_DATA) public data: number
  ) {
    this.userId = data;
  }

  ngOnInit(): void {}
}
