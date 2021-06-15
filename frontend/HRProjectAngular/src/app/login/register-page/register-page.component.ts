import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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


  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private httpRequestService: HTTPReq
  ) { }

  ngOnInit(): void {
    if (localStorage.getItem("isLogged") == "true") {
      this.router.navigate(['/employee']);
    }
  }

  this

}
