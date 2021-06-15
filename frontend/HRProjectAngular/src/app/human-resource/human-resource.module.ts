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
import { DragDropModule } from '@angular/cdk/drag-drop';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HomeComponent } from './components/home/home.component';
import { HireComponent } from './components/hire/hire.component';
import { CheckEmployeeComponent } from './components/check-employee/check-employee.component';
import { VisaComponent } from './components/visa/visa.component';
import { NavComponent } from './components/human-resource-nav/nav.component';
import { HousingComponent } from './components/housing/housing.component';
import { PersonalInfoComponent } from './components/personal-info/personal-info.component';
import { HrPageComponent } from './components/hr-page/hr-page.component';
import { MatMenuModule} from "@angular/material/menu";

import { HttpClientModule } from '@angular/common/http';
import { HTTPReq } from '../service/HTTPReq/HTTPReq.service';

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
    MatTableModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatMenuModule,
    HttpClientModule
  ],
  providers: [HTTPReq],
  exports: [NavComponent],
})
export class HumanResourceModule {}
