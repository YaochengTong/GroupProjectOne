import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HumanResourceRoutingModule } from './human-resource-routing.module';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatGridListModule } from '@angular/material/grid-list';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { MatCardModule } from '@angular/material/card';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './components/home/home.component';
import { HireComponent } from './components/hire/hire.component';
import { CheckEmployeeComponent } from './components/check-employee/check-employee.component';
import { VisaComponent } from './components/visa/visa.component';
import { NavComponent } from './components/human-resource-nav/nav.component';
import { PersonalInfoComponent } from './components/personal-info/personal-info.component';
import { HrPageComponent } from './components/hr-page/hr-page.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatDialogModule} from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';


import { HttpClientModule } from '@angular/common/http';
import { HTTPReq } from '../service/HTTPReq/HTTPReq.service';



import { NameSectionDialogComponent } from './components/personal-info/name-section-dialog/name-section-dialog.component';
import { AddressSectionDialogComponent } from './components/personal-info/address-section-dialog/address-section-dialog.component';
import { ContactSectionDialogComponent } from './components/personal-info/contact-section-dialog/contact-section-dialog.component';
import { EmergencyContactSectionDialogComponent } from './components/personal-info/emergency-contact-section-dialog/emergency-contact-section-dialog.component';
import { EmploymentSectionDialogComponent } from './components/personal-info/employment-section-dialog/employment-section-dialog.component';


@NgModule({
  declarations: [
    HomeComponent,
    NavComponent,
    PersonalInfoComponent,
    VisaComponent,
    CheckEmployeeComponent,
    HireComponent,
    HrPageComponent,
    NameSectionDialogComponent,
    AddressSectionDialogComponent,
    ContactSectionDialogComponent,
    EmergencyContactSectionDialogComponent,
    EmploymentSectionDialogComponent
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
    MatTableModule,
    MatInputModule,
    MatGridListModule,
    FormsModule,
    ReactiveFormsModule,
    MatMenuModule,
    MatCardModule,
    HttpClientModule,
    MatSnackBarModule,
    MatDialogModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
  ],
  providers: [HTTPReq],
  exports: [
    NavComponent,
    HomeComponent,
    PersonalInfoComponent,
    VisaComponent,
    NameSectionDialogComponent,
    AddressSectionDialogComponent,
    ContactSectionDialogComponent,
    EmergencyContactSectionDialogComponent,
    EmploymentSectionDialogComponent],
})
export class HumanResourceModule {}
