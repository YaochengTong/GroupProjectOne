import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { House } from '../domain/house';
import { HousingService } from '../housing.service';

@Component({
  selector: 'app-housing-detail',
  templateUrl: './housing-detail.component.html',
  styleUrls: ['./housing-detail.component.scss'],
})
export class HousingDetailComponent implements OnInit {
  house: House | undefined;

  constructor(
    private route: ActivatedRoute,
    private housingService: HousingService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getHouse();
  }

  getHouse(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.housingService.getHouseById(id).subscribe((h) => (this.house = h));
  }

  goBack(): void {
    this.location.back();
  }
}
