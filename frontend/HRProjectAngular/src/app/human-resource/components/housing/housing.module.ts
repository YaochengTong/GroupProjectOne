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
import { ReportDialogComponent } from './report-dialog/report-dialog.component';
import {MatExpansionModule} from "@angular/material/expansion";


@NgModule({
  declarations: [
    HousingPageComponent,
    HousingDetailComponent,
    ReportDialogComponent,
  ],
  imports: [
    CommonModule,
    HousingRoutingModule,
    MatCardModule,
    FormsModule,
    MatGridListModule,
    MatButtonModule,
    MatRippleModule,
    MatListModule,
    MatExpansionModule,
  ],
})
export class HousingModule {}
