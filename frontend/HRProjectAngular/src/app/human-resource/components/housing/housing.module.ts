import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HousingRoutingModule } from './housing-routing.module';
import { HousingPageComponent } from './housing-page/housing-page.component';


@NgModule({
  declarations: [
    HousingPageComponent
  ],
  imports: [
    CommonModule,
    HousingRoutingModule
  ]
})
export class HousingModule { }
