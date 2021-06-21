import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CheckEmployeeRoutingModule } from './check-employee-routing.module';
import { EmployeePageComponent } from './employee-page/employee-page.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  declarations: [EmployeePageComponent, EmployeeDetailsComponent],
  imports: [
    CommonModule,
    CheckEmployeeRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatButtonModule,
  ],
})
export class CheckEmployeeModule {}
