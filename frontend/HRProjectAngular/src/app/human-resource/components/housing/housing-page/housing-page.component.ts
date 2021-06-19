import { Component, OnInit } from '@angular/core';
import { House } from '../domain/house';
import { HousingService } from '../housing.service';

@Component({
  selector: 'app-housing-page',
  templateUrl: './housing-page.component.html',
  styleUrls: ['./housing-page.component.scss'],
})
export class HousingPageComponent implements OnInit {
  houses: House[] = [];
  selectedHouse?: House;

  constructor(private housingService: HousingService) {}

  ngOnInit(): void {
    // @ts-ignore
    this.housingService.getHouses().subscribe((h) => {
      this.houses = h;
      console.log(h);
    });

    // console.log(this.houses);
  }

  onSelect(h: House): void {
    this.selectedHouse = h;
  }

  public onCardClick(h: House) {
    this.selectedHouse = h;
  }
}
