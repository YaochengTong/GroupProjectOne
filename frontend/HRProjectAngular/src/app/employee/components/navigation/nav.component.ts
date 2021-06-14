import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';

@Component({
  selector: 'employee-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class EmployeeNavComponent implements OnInit {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
  .pipe(
    map(result => result.matches),
    shareReplay()
  );

  showFiller:boolean = false;
  homeColor: string = 'primary';
  personColor: string = 'basic';
  visaColor: string = 'basic';
  housingColor: string = 'basic';

  selectedStyle: any = {
    "color": "#3F51B5"
  };

  unselectedStyle: any = {
    "color": "black"
  }

  homeStyle: any = {
    "color": "#3F51B5"
  };
  personStyle: any = {};
  visaStyle: any = {};
  housingStyle: any = {};


  constructor(private router: Router, private breakpointObserver: BreakpointObserver) { }

  ngOnInit(): void {
  }

  clickHome(): void {
    this.homeColor = 'primary';
    this.personColor = 'basic';
    this.visaColor = 'basic';
    this.housingColor = 'basic';

    this.homeStyle = this.selectedStyle;
    this.personStyle = this.unselectedStyle;
    this.visaStyle = this.unselectedStyle;
    this.housingStyle = this.unselectedStyle;

    this.router.navigate(['/employee/home']);
  }

  clickPeronalInfo(): void{
    this.homeColor = 'basic';
    this.personColor = 'primary';
    this.visaColor = 'basic';
    this.housingColor = 'basic';

    this.homeStyle = this.unselectedStyle;
    this.personStyle = this.selectedStyle;
    this.visaStyle = this.unselectedStyle;
    this.housingStyle = this.unselectedStyle;

    this.router.navigate(['/employee/personalInfo']);
  }

  clickVisa(): void{
    this.homeColor = 'basic';
    this.personColor = 'basic';
    this.visaColor = 'primary';
    this.housingColor = 'basic';

    this.homeStyle = this.unselectedStyle;
    this.personStyle = this.unselectedStyle;
    this.visaStyle = this.selectedStyle;
    this.housingStyle = this.unselectedStyle;

    this.router.navigate(['/employee/visa']);
  }

  clickHousing(): void {
    this.homeColor = 'basic';
    this.personColor = 'basic';
    this.visaColor = 'basic';
    this.housingColor = 'primary';

    this.homeStyle = this.unselectedStyle;
    this.personStyle = this.unselectedStyle;
    this.visaStyle = this.unselectedStyle;
    this.housingStyle = this.selectedStyle;

    this.router.navigate(['/employee/housing']);
  }

}
