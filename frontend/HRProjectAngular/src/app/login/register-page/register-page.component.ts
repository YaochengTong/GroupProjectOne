import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {

  registerForm! : FormGroup;
  loading = false;
  submitted = false;


  email: string = "email";
  registerToken: string = "token";


  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private httpRequestService: HTTPReq,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    if (localStorage.getItem("isLogged") == "true") {
      // if (localStorage.getItem("user").roleName == 'HR') {
      //   this.router.navigate(['/human-resource'])
      // } else {
      //   this.router.navigate(['/employee']);
      // } 
    }

    this.route.queryParams.subscribe(params => {
      this.email = params.email;
      this.registerToken = params.token;
    });

    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    })
  }

  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;

    let params = {
      "username": this.f.username.value,
      "password": this.f.password.value,
      "email": this.email,
      "token": this.registerToken
    }

    this.httpRequestService.postData('/user/register',
    params,
    'http://localhost:9999').subscribe(
      (data: any) => {
        console.log(data)
        if (data.success === true) {
          localStorage.setItem("isLogged", "true");
          //localStorage.setItem("registerToken", this.registerToken);
          localStorage.setItem("token", data.JWT_TOKEN);
          //localStorage.setItem("user", JSON.stringify(data.user));
          localStorage.setItem("email", data.email);
          localStorage.setItem("userId", data.userId);
          this.router.navigate(['/on-boarding'])
        } else {
          console.log(data);
        }
      }
    );
  }
  

}

