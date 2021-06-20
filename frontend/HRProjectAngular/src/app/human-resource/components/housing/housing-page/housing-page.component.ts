import { Component, OnInit } from '@angular/core';
import { House } from '../domain/house';
import { HousingService } from '../housing.service';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';

@Component({
  selector: 'app-housing-page',
  templateUrl: './housing-page.component.html',
  styleUrls: ['./housing-page.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition(
        'expanded <=> collapsed',
        animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')
      ),
    ]),
  ],
})
export class HousingPageComponent implements OnInit {
  houses: House[] = [];
  selectedHouse?: House;
  isDataAvailable: boolean = false;

  constructor(private housingService: HousingService) {}

  ngOnInit(): void {
    // @ts-ignore
    this.housingService.getHouses().subscribe((h) => {
      this.houses = h;
      console.log(h);
      this.isDataAvailable = true;
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
