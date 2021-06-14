import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EmployeeProfileComponent } from './components/employeeProfile/employeeProfile.component';
import { HireComponent } from './components/hire/hire.component';
import { HouseManagementComponent } from './components/houseManagement/houseManagement.component';
import { visaStatusManagementComponent } from './components/visaStatusManagement/visaStatusManagement.component';
import { HRHomeComponent } from './components/HRHome/HRHome.component';

const routes: Routes = [
  {path: '', component: HRHomeComponent},
  {path: 'home', component: HRHomeComponent},
  {path: 'housing', component: HouseManagementComponent},
  {path: 'employeeProfile', component: EmployeeProfileComponent},
  {path: 'visaManagement', component: visaStatusManagementComponent},
  {path: 'hire', component: HireComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class HumanResourceRoutingModule { }
