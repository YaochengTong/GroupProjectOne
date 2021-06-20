import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  loginForm!: FormGroup;
  loading = false;
  isUsername = true;
  isEmail = false;
  submitted = false;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private httpRequestService: HTTPReq
  ) {}

  ngOnInit() {
    // redirect to home if already logged in
    if (localStorage.getItem('isLogged') == 'true') {
      // if (localStorage.getItem("user")!.roleName == 'HR') {
      //   this.router.navigate(['/human-resource'])
      // } else {
      //   this.router.navigate(['/employee']);
      // }
    }

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      // email: ['', [Validators.required, Validators.email]]
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  switchLoginType() {
    this.isUsername = !this.isUsername;
    this.isEmail = !this.isEmail;
    if (this.isUsername) {
      this.loginForm = this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required],
      });
    } else {
      this.loginForm = this.formBuilder.group({
        password: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
      });
    }
  }

  onSubmit() {
    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;

    let params;
    if (this.isUsername) {
      params = {
        username: this.f.username.value,
        password: this.f.password.value,
      };
    } else {
      params = {
        password: this.f.password.value,
        email: this.f.email.value,
      };
    }

    this.httpRequestService
      .postData('/user/login', params, 'http://localhost:9999')
      .subscribe((data: any) => {
        console.log(data);
        localStorage.clear();
        localStorage.setItem('isLogged', 'true');
        localStorage.setItem('token', data.JWT_TOKEN);
        localStorage.setItem('user', JSON.stringify(data.user));
        localStorage.setItem('userId', data.user.id);

        //console.log(data.user.activated)
        if(data.user.activated === false){
          if(data.application_status.trim() === 'Pending'){
            this.router.navigate(['/pending']);
          }
          else if(data.application_status.trim()  === 'Rejected'){
            this.router.navigate(['/on-boarding'], {
              queryParams: {
                rejected: true
              }
            });
          }
          return;
        }

        console.log(localStorage.getItem('user'));
        if (data.user.roleName == 'HR') {
          this.router.navigate(['/human-resource']);
        } else {
          this.router.navigate(['/employee']);
        }
      });
  }
}
