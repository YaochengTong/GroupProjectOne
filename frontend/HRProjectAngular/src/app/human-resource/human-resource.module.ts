import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HRHomeComponent } from './components/HRHome/HRHome.component';
import { HRNavComponent } from './components/nav/nav.component';
import { HireComponent } from './components/hire/hire.component';
import { visaStatusManagementComponent } from './components/visaStatusManagement/visaStatusManagement.component';
import { HouseManagementComponent } from './components/houseManagement/houseManagement.component';
import { EmployeeProfileComponent } from './components/employeeProfile/employeeProfile.component';

import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatMenuModule} from '@angular/material/menu';

import { HumanResourceRoutingModule } from './human-resource-routing.module';

@NgModule({
  declarations: [
    HRHomeComponent,
    HRNavComponent,
    HireComponent,
    visaStatusManagementComponent,
    HouseManagementComponent,
    EmployeeProfileComponent
  ],
  imports: [
    CommonModule,
    HumanResourceRoutingModule,
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
    MatMenuModule
  ],
  exports: [
    CommonModule, 
    HRHomeComponent,
    HRNavComponent,
    HireComponent,
    visaStatusManagementComponent,
    HouseManagementComponent,
    EmployeeProfileComponent
  ],
  bootstrap: [HRNavComponent]
})

export class HumanResourceModule { }
