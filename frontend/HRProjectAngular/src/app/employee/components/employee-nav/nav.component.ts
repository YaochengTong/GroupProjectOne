import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'employee-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss'],
})
export class EmployeeNavComponent implements OnInit {
  isHandset$: Observable<boolean> = this.breakpointObserver
    .observe(Breakpoints.Handset)
    .pipe(
      map((result) => result.matches),
      shareReplay()
    );
  username: string = '';
  showMenu = false;
  avatar!: string;

  constructor(
    private breakpointObserver: BreakpointObserver,
    private router: Router
  ) {}

  ngOnInit(): void {
    let retrievedObject: any = localStorage.getItem('user');
    let user = JSON.parse(retrievedObject);
    this.username = user.username;
    if (localStorage.getItem("avatar") == null) { this.avatar = "https://markdown-bucket.s3.us-east-2.amazonaws.com/uPic/png-clipart-login-computer-icons-avatar-icon-monochrome-black-thumbnail_2021_06_16_17_09_28.png"};
    if (localStorage.getItem("avatar") != null) {this.avatar = localStorage.getItem("avatar")!;}
  }

  onLogout() {
    localStorage.clear();
    this.router.navigateByUrl('login');
  }
}
