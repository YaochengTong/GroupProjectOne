import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit{

  loginForm!: FormGroup;
  loading = false;
  loginByEmail = false;
  submitted = false;
  endPoint: string = "http://localhost:9999/user/";

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private httpRequestService: HTTPReq
    ) {
      if (localStorage.getItem("isLogged") == "true") {
        this.router.navigate(['/employee']);
      }
    }

  ngOnInit() {
    // redirect to home if already logged in
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }


  get f() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;

    if (this.loginForm.invalid) { return; }

    this.loading = true;

    console.log(this.f.username.value);
    console.log(this.f.password.value);
    

    const formData: Map<String, Object> = new Map<String, Object>();
    formData.set("username", this.f.username.value);
    formData.set("password", this.f.password.value);
    
    this.httpRequestService.postData('/user/login', 
    formData, 
    'http://localhost:9999').subscribe(
      (data: any) => {
        console.log(data);
        // localStorage.setItem("isLogged", "true");
        // if (data.get("roleName") == 'HR') {
        //   this.router.navigate(['/human-resource'])
        // } else {
        //   this.router.navigate(['/employee']);
        // }
      },
      (error) => {
        console.log(error);
        this.loading = false;
      }
    )

  }

  // onLogin() {
  //   localStorage.setItem('isLogged', 'true');
  //   this.router.navigate(['/employee']);
  // }
}
