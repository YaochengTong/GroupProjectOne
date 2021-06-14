import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HumanResourceRoutingModule } from './human-resource-routing.module';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { HomeComponent } from './components/home/home.component';
import { HireComponent } from './components/hire/hire.component';
import { CheckEmployeeComponent } from './components/check-employee/check-employee.component';
import { VisaComponent } from './components/visa/visa.component';
import { NavComponent } from './components/nav/nav.component';
import { HousingComponent } from './components/housing/housing.component';
import { PersonalInfoComponent } from './components/personal-info/personal-info.component';
import { HrPageComponent } from './components/hr-page/hr-page.component';

@NgModule({
  declarations: [
    HousingComponent,
    HomeComponent,
    NavComponent,
    PersonalInfoComponent,
    VisaComponent,
    CheckEmployeeComponent,
    HireComponent,
    HrPageComponent,
  ],
  imports: [
    CommonModule,
    HumanResourceRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    DragDropModule,
  ],
  exports: [NavComponent],
})
export class HumanResourceModule {}
