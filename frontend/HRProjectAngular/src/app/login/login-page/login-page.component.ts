import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit{

  loginForm!: FormGroup;
  loading = false;
  isUsername = true;
  isEmail = false;
  submitted = false;
  endPoint: string = "http://localhost:9999/user/";

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private httpRequestService: HTTPReq
    ) {}

  ngOnInit() {
    // redirect to home if already logged in
    if (localStorage.getItem("isLogged") == "true") {
      this.router.navigate(['/employee']);
    }

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    })
  }


  get f() { return this.loginForm.controls; }


  onSubmit() {
    this.submitted = true;

    if (this.loginForm.invalid) { return; }

    this.loading = true;

    console.log(this.f.username.value);
    console.log(this.f.password.value);
  

   let params = {
    "username": this.f.username.value,
    "password": this.f.password.value,
    "email": this.f.email.value
   }

    this.httpRequestService.postData('/user/login', 
    params, 
    'http://localhost:9999').subscribe(
      (data: any) => {
        console.log(data);
        localStorage.setItem("isLogged", "true");
        localStorage.setItem("token", data.JWT_TOKEN);
        localStorage.setItem("user", JSON.stringify(data.user));
        if (data.user.roleName == 'HR') {
          this.router.navigate(['/human-resource'])
        } else {
          this.router.navigate(['/employee']);
        }
      },
      (error) => {
        console.log(error);
        this.loading = false;
      }
    )

  }
}
