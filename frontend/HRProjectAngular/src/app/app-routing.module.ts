import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeModule } from './employee/employee.module';
import { HumanResourceModule } from './human-resource/human-resource.module';
import { LoginModule } from './login/login.module';
import { PageNotFoundComponent } from './shared/page-not-found/page-not-found.component';
import { AuthGuard } from './core/auth.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  { path: 'login', loadChildren: () => LoginModule },
  {
    path: 'employee',
    loadChildren: () => EmployeeModule,
    //canActivate: [AuthGuard],
  },
  {
    path: 'human-resource',
    loadChildren: () => HumanResourceModule,
    //canActivate: [AuthGuard],
  },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
