import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PersonalInfoComponent } from './components/personalInfo/personalInfo.component';
import { HousingComponent } from './components/housing/housing.component';
import { VisaComponent } from './components/visa/visa.component';
import { EmployeePageComponent } from './components/employee-page/employee-page.component';

const routes: Routes = [
  {
    path: '',
    component: EmployeePageComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'housing', component: HousingComponent },
      { path: 'personal-info', component: PersonalInfoComponent },
      { path: 'visa', component: VisaComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EmployeeRoutingModule {}
