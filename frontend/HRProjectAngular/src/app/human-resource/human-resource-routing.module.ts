import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PersonalInfoComponent } from './components/personal-info/personal-info.component';
import { VisaComponent } from './components/visa/visa.component';
import { HireComponent } from './components/hire/hire.component';
import { HrPageComponent } from './components/hr-page/hr-page.component';
import { HousingModule } from './components/housing/housing.module';
import { CheckEmployeeModule } from './components/check-employee/check-employee.module';

const routes: Routes = [
  {
    path: '',
    component: HrPageComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'personal-info', component: PersonalInfoComponent },
      { path: 'visa', component: VisaComponent },
      { path: 'check-employee', loadChildren: () => CheckEmployeeModule },
      { path: 'housing', loadChildren: () => HousingModule },
      { path: 'hire', component: HireComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HumanResourceRoutingModule {}
