import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PersonalInfoComponent } from './components/personalInfo/personalInfo.component';
import { VisaComponent } from './components/visa/visa.component';
import { EmployeePageComponent } from './components/employee-page/employee-page.component';
import { OnBoardingComponent } from '../on-boarding/on-boarding.component';
import { HousingModule } from './components/housing/housing.module';

const routes: Routes = [
  {
    path: '',
    component: EmployeePageComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'housing', loadChildren: () => HousingModule },
      { path: 'personal-info', component: PersonalInfoComponent },
      { path: 'visa', component: VisaComponent },
      { path: 'on-boarding', component: OnBoardingComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EmployeeRoutingModule {}
