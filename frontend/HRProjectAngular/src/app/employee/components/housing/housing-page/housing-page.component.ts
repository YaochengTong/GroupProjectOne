import { Component, OnInit } from '@angular/core';
import { House } from '../domain/house';
import { HouseService } from '../house.service';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { Employee } from '../domain/employee';

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
  house?: House;
  employees?: Employee[];
  userId: number | undefined;
  isDataAvailable: boolean = false;

  constructor(private hs: HouseService) {}

  ngOnInit(): void {
    this.userId = Number(localStorage.getItem('userId'));
    this.hs.getHouseInfoByUserId(this.userId).subscribe((h) => {
      this.house = h;
      this.employees = h.houseEmployeeInfoList;
      this.isDataAvailable = true;
      console.log(h);
    });
  }

  reportFacility() {}
}
