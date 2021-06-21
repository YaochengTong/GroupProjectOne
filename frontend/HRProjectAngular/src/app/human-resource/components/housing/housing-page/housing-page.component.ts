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
import { FormBuilder, Validators } from '@angular/forms';

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

  houseForm = this.fb.group({
    address: [null, Validators.required],
    numberOfPerson: [null, Validators.required],
    userId: [null, Validators.required],
  });

  userId = 0;

  constructor(
    private housingService: HousingService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.userId = Number(localStorage.getItem('userId'));

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

  refresh(): void {}

  onSubmit() {
    let house = new House(this.houseForm.value);
    this.housingService
      .addHouse(house, this.houseForm.value.userId)
      .subscribe(() => this.refresh());
    alert('House added');
  }
}
