import { Component, OnInit } from '@angular/core';
import { HOUSES } from '../domain/mock-houses';
import { House } from '../domain/house';

@Component({
  selector: 'app-housing-page',
  templateUrl: './housing-page.component.html',
  styleUrls: ['./housing-page.component.scss'],
})
export class HousingPageComponent implements OnInit {
  houses = HOUSES;
  selectedHouse?: House;

  constructor() {}

  ngOnInit(): void {}

  onSelect(h: House): void {
    this.selectedHouse = h;
  }

  public onCardClick(h: House) {
     this.selectedHouse = h;

  }
}
