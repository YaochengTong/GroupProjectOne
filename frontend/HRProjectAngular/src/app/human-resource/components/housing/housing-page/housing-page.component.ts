import { Component, OnInit } from '@angular/core';
import { House } from '../domain/house';
import { HousingService } from '../housing.service';
import { HTTPReq } from '../../../../service/HTTPReq/HTTPReq.service';

@Component({
  selector: 'app-housing-page',
  templateUrl: './housing-page.component.html',
  styleUrls: ['./housing-page.component.scss'],
})
export class HousingPageComponent implements OnInit {
  houses: House[] = [];
  selectedHouse?: House;

  constructor(
    private housingService: HousingService,
    private httpClient: HTTPReq
  ) {}

  ngOnInit(): void {
    this.httpClient
      .getData('/get-houses', null, 'http://localhost:8999')
      .subscribe((data: any) => {
        console.log(data.allHouse);
      });

    this.housingService.getHouses().subscribe((h) => (this.houses = h));
  }

  onSelect(h: House): void {
    this.selectedHouse = h;
  }

  public onCardClick(h: House) {
    this.selectedHouse = h;
  }
}
