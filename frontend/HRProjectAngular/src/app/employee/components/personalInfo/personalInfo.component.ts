import { Component, OnInit } from '@angular/core';

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

  constructor() {}

  ngOnInit(): void {}
}
