import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { JsonpClientBackend } from '@angular/common/http';

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
    if (localStorage.getItem("isLogged") == "true") {
      // if (localStorage.getItem("user")!.roleName == 'HR') {
      //   this.router.navigate(['/human-resource'])
      // } else {
      //   this.router.navigate(['/employee']);
      // }
    }

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
      // email: ['', [Validators.required, Validators.email]]
    })
  }

  get f() {
    return this.loginForm.controls;
  }

  onToggle() {
    this.isUsername = !this.isUsername;
    this.isEmail = !this.isEmail;
    if (this.isUsername) {
      this.loginForm = this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required]
      })
    } else {
      this.loginForm = this.formBuilder.group({
        password: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]]
      })
    }
  }


  onSubmit() {
    console.log("ese");
    console.log(this.f.username.value);
    console.log(this.f.password.value);  

    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }
    
    console.log("eee")

    this.loading = true;

    
   let params = {
    "username": this.f.username.value,
    "password": this.f.password.value,
    // "email": this.f.email.value
   }

    this.httpRequestService.postData('/user/login', 
    params, 
    'http://localhost:9999').subscribe(
      (data: any) => {
        console.log(data);
        localStorage.setItem("isLogged", "true");
        localStorage.setItem("token", data.JWT_TOKEN);
        localStorage.setItem("user", JSON.stringify(data.user));
        console.log(localStorage.getItem("user"));
        if (data.user.roleName == 'HR') {
          this.router.navigate(['/human-resource'])
        } else {
          this.router.navigate(['/employee']);
        }
      });
      
    }
}
