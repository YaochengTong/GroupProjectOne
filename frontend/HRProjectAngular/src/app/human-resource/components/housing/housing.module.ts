import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HousingRoutingModule } from './housing-routing.module';
import { HousingPageComponent } from './housing-page/housing-page.component';
import {MatCardModule} from "@angular/material/card";
import {FormsModule} from "@angular/forms";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatButtonModule} from "@angular/material/button";
import {MatRippleModule} from "@angular/material/core";
import {MatListModule} from "@angular/material/list";
import { HousingDetailComponent } from './housing-detail/housing-detail.component';


@NgModule({
  declarations: [HousingPageComponent, HousingDetailComponent],
  imports: [
    CommonModule,
    HousingRoutingModule,
    MatCardModule,
    FormsModule,
    MatGridListModule,
    MatButtonModule,
    MatRippleModule,
    MatListModule,
  ],
})
export class HousingModule {}
