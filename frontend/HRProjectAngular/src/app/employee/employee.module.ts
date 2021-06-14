import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import { HousingComponent } from './components/housing/housing.component';
import { EmployeeNavComponent } from './components/employee-nav/nav.component';
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
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { ReactiveFormsModule } from '@angular/forms';
import { OnBoardingComponent } from './components/on-boarding/on-boarding.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
  declarations: [
    HomeComponent,
    HousingComponent,
    EmployeeNavComponent,
    PersonalInfoComponent,
    VisaComponent,
    EmployeePageComponent,
    OnBoardingComponent,
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
    MatSelectModule,
    MatRadioModule,
    ReactiveFormsModule,
    LayoutModule,
    MatDatepickerModule,
    MatNativeDateModule,
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
