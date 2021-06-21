import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
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
import { MatDialogModule } from '@angular/material/dialog';

import { EmployeeRoutingModule } from './employee-routing.module';
import { EmployeePageComponent } from './components/employee-page/employee-page.component';
import { HumanResourceModule } from '../human-resource/human-resource.module';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { OnBoardingComponent } from '../on-boarding/on-boarding.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatStepperModule } from '@angular/material/stepper';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatBadgeModule } from '@angular/material/badge';
import { MatSnackBarModule } from '@angular/material/snack-bar';

import { HTTPReq } from '../service/HTTPReq/HTTPReq.service';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { NgxMatFileInputModule } from '@angular-material-components/file-input';
import { NameSectionDialogComponent } from './components/personalInfo/name-section-dialog/name-section-dialog.component';
import { AddressSectionDialogComponent } from './components/personalInfo/address-section-dialog/address-section-dialog.component';
import { ContactSectionDialogComponent } from './components/personalInfo/contact-section-dialog/contact-section-dialog.component';
import { EmergencyContactSectionDialogComponent } from './components/personalInfo/emergency-contact-section-dialog/emergency-contact-section-dialog.component';
import { EmploymentSectionDialogComponent } from './components/personalInfo/employment-section-dialog/employment-section-dialog.component';
import { ContactTemplateComponent } from '../on-boarding/contact-template/contact-template.component';
import { PersonalInfoTemplateComponent } from '../on-boarding/personal-info-template/personal-info-template.component';
import { VisaNotificationComponent } from '../human-resource/components/visa/visa-notification/visa-notification.component';

@NgModule({
  declarations: [
    HomeComponent,
    EmployeeNavComponent,
    PersonalInfoComponent,
    VisaComponent,
    VisaNotificationComponent,
    EmployeePageComponent,
    OnBoardingComponent,
    ContactTemplateComponent,
    PersonalInfoTemplateComponent,
    NameSectionDialogComponent,
    AddressSectionDialogComponent,
    ContactSectionDialogComponent,
    EmergencyContactSectionDialogComponent,
    EmploymentSectionDialogComponent,
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
    LayoutModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    MatDialogModule,
    FlexLayoutModule,
    NgxMatFileInputModule,
    FormsModule,
    MatStepperModule,
    MatProgressSpinnerModule,
    MatBadgeModule,
    MatSnackBarModule,
  ],
  exports: [
    CommonModule,
    HomeComponent,
    EmployeeNavComponent,
    PersonalInfoComponent,
    VisaComponent,
    NameSectionDialogComponent,
    AddressSectionDialogComponent,
    ContactSectionDialogComponent,
    EmergencyContactSectionDialogComponent,
    EmploymentSectionDialogComponent,
  ],

  providers: [HTTPReq],

  bootstrap: [EmployeeNavComponent],
})
export class EmployeeModule {}
