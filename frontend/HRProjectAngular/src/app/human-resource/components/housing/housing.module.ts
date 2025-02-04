import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HousingRoutingModule } from './housing-routing.module';
import { HousingPageComponent } from './housing-page/housing-page.component';
import { MatCardModule } from '@angular/material/card';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatButtonModule } from '@angular/material/button';
import { MatRippleModule } from '@angular/material/core';
import { MatListModule } from '@angular/material/list';
import { HousingDetailComponent } from './housing-detail/housing-detail.component';
import { ReportDialogComponent } from './report-dialog/report-dialog.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AddHouseDialogComponent } from './add-house-dialog/add-house-dialog.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [
    HousingPageComponent,
    HousingDetailComponent,
    ReportDialogComponent,
    AddHouseDialogComponent,
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
    MatProgressSpinnerModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
  ],
})
export class HousingModule {}
