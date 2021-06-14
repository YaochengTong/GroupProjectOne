import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';

@Component({
  selector: 'hr-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class HRNavComponent implements OnInit {
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );


  constructor(private router: Router, private breakpointObserver: BreakpointObserver) {}

  showFiller:boolean = false;
  homeColor: string = 'primary';
  profileColor: string = 'basic';
  visaColor: string = 'basic';
  houseColor: string = 'basic';
  hireColor: string = 'basic';

  selectedStyle: any = {
    "color": "#3F51B5"
  };

  unselectedStyle: any = {
    "color": "black"
  }

  homeStyle: any = {
    "color": "#3F51B5"
  };
  profileStyle: any = {};
  visaStyle: any = {};
  houseStyle: any = {};
  hireStyle: any = {};

  ngOnInit(): void {
  }

  clickHome(): void {
    this.homeColor = 'primary';
    this.profileColor = 'basic';
    this.visaColor = 'basic';
    this.houseColor = 'basic';
    this.hireColor = 'basic'

    this.homeStyle = this.selectedStyle;
    this.profileStyle = this.unselectedStyle;
    this.visaStyle = this.unselectedStyle;
    this.houseStyle = this.unselectedStyle;
    this.hireStyle = this.unselectedStyle;
    this.router.navigate(['/hr/home']);
  }

  clickProfile(): void {
    this.homeColor = 'basic';
    this.profileColor = 'primary';
    this.visaColor = 'basic';
    this.houseColor = 'basic';
    this.hireColor = 'basic'

    this.homeStyle = this.unselectedStyle;
    this.profileStyle = this.selectedStyle;
    this.visaStyle = this.unselectedStyle;
    this.houseStyle = this.unselectedStyle;
    this.hireStyle = this.unselectedStyle;
    this.router.navigate(['/hr/employeeProfile']);
  }

  clickVisa(): void {
    this.homeColor = 'basic';
    this.profileColor = 'basic';
    this.visaColor = 'primary';
    this.houseColor = 'basic';
    this.hireColor = 'basic'

    this.homeStyle = this.unselectedStyle;
    this.profileStyle = this.unselectedStyle;
    this.visaStyle = this.selectedStyle;
    this.houseStyle = this.unselectedStyle;
    this.hireStyle = this.unselectedStyle;
    this.router.navigate(['/hr/visaManagement']);
  }

  clickHousing(): void {
    this.homeColor = 'basic';
    this.profileColor = 'basic';
    this.visaColor = 'basic';
    this.houseColor = 'primary';
    this.hireColor = 'basic'

    this.homeStyle = this.unselectedStyle;
    this.profileStyle = this.unselectedStyle;
    this.visaStyle = this.unselectedStyle;
    this.houseStyle = this.selectedStyle;
    this.hireStyle = this.unselectedStyle;
    this.router.navigate(['/hr/housing']);
  }

  clickHire(): void {
    this.homeColor = 'basic';
    this.profileColor = 'basic';
    this.visaColor = 'basic';
    this.houseColor = 'basic';
    this.hireColor = 'primary'

    this.homeStyle = this.unselectedStyle;
    this.profileStyle = this.unselectedStyle;
    this.visaStyle = this.unselectedStyle;
    this.houseStyle = this.unselectedStyle;
    this.hireStyle = this.selectedStyle;
    this.router.navigate(['/hr/hire']);
  }

}
