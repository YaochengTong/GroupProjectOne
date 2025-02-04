import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeModule } from './employee/employee.module';
import { HumanResourceModule } from './human-resource/human-resource.module';
import { LoginModule } from './login/login.module';
import { PageNotFoundComponent } from './shared/page-not-found/page-not-found.component';
import { AuthGuardForEmployee } from './core/auth.guardForEmployee';
import { AuthGuardForHR } from './core/auth.guardForHR';
import { OnBoardingComponent } from './on-boarding/on-boarding.component';
import { PendingComponent } from './shared/pending/pending.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'on-boarding',
    component: OnBoardingComponent,
  },
  {
    path: 'login',
    loadChildren: () => LoginModule,
  },
  {
    path: 'employee',
    loadChildren: () => EmployeeModule,
    canActivate: [AuthGuardForEmployee],
  },
  {
    path: 'human-resource',
    loadChildren: () => HumanResourceModule,
    canActivate: [AuthGuardForHR],
  },
  { 
    path: 'pending', 
    component: PendingComponent 
  },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
