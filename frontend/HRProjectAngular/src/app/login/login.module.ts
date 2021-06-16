import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginPageComponent } from './login-page/login-page.component';
import { ExtendedModule, FlexModule } from '@angular/flex-layout';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { RegisterPageComponent } from './register-page/register-page.component';

@NgModule({
  declarations: [LoginPageComponent, RegisterPageComponent],
  imports: [
    CommonModule,
    LoginRoutingModule,
    FlexModule,
    ExtendedModule,
    ReactiveFormsModule,
    MatSlideToggleModule,
  ],
  exports: [LoginPageComponent],
})
export class LoginModule {}
