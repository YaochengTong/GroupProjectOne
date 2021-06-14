import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import { HousingComponent } from './components/housing/housing.component';
import { EmployeeNavComponent } from './components/navigation/nav.component';
import { PersonalInfoComponent } from './components/personalInfo/personalInfo.component';
import { VisaComponent } from './components/visa/visa.component';

import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatListModule } from '@angular/material/list';
import { MatDividerModule } from '@angular/material/divider';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';

import { EmployeeRoutingModule } from './employee-routing.module';
import { EmployeePageComponent } from './components/employee-page/employee-page.component';
import { HumanResourceModule } from '../human-resource/human-resource.module';

@NgModule({
  declarations: [
    HomeComponent,
    HousingComponent,
    EmployeeNavComponent,
    PersonalInfoComponent,
    VisaComponent,
    EmployeePageComponent,
  ],
  imports: [
    CommonModule,
    EmployeeRoutingModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatGridListModule,
    MatListModule,
    MatDividerModule,
    MatTableModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatMenuModule,
    HumanResourceModule,
  ],
  exports: [
    CommonModule,
    HomeComponent,
    HousingComponent,
    EmployeeNavComponent,
    PersonalInfoComponent,
    VisaComponent,
  ],

  bootstrap: [EmployeeNavComponent],
})
export class EmployeeModule {}
