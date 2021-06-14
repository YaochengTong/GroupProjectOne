import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {HomeComponent} from './components/home/home.component';
import {HousingComponent} from './components/housing/housing.component';
import {PersonalInfoComponent} from './components/personalInfo/personalInfo.component';
import {VisaComponent} from './components/visa/visa.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'housing', component: HousingComponent},
  {path: 'personalInfo', component: PersonalInfoComponent},
  {path: 'visa', component: VisaComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeRoutingModule { }
