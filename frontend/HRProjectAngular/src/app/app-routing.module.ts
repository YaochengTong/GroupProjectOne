import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeModule } from './employee/employee.module';
import { HumanResourceModule } from './human-resource/human-resource.module';

const routes: Routes = [
  {
    path: 'employee', loadChildren: ()=> EmployeeModule
  },
  {
    path: 'hr', loadChildren: () => HumanResourceModule
  }
]

@NgModule({
  imports: [EmployeeModule, HumanResourceModule, RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
