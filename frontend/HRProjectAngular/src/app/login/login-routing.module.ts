import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { MatSelectModule } from '@angular/material/select';
const routes: Routes = [{ path: '', component: LoginPageComponent }];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    FormsModule,
    ReactiveFormsModule,
    // MatSelectModule
  ],
  exports: [RouterModule],
})
export class LoginRoutingModule {}
