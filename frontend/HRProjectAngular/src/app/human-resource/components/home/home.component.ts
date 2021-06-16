import { Component } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  /** Based on the screen size, switch from standard to one column per row */
  // cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
  //   map(({ matches }) => {
  //     if (matches) {
  //       return [
  //         { title: 'Info', cols: 1, rows: 1 },
  //         { title: 'Visa', cols: 1, rows: 1 },
  //         { title: 'Housing', cols: 1, rows: 1 },
  //       ];
  //     }

  //     return [
  //       { title: 'Info', cols: 1, rows: 1 },
  //       { title: 'Visa', cols: 1, rows: 1 },
  //       { title: 'Housing', cols: 1, rows: 1 },
  //     ];
  //   })
  // );
  // userName: string = 'User Name';

  // constructor(private breakpointObserver: BreakpointObserver) {}
}
