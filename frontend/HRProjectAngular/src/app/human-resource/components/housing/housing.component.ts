import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { map } from 'rxjs/operators';
import { House } from './house';

@Component({
  selector: 'app-housing',
  templateUrl: './housing.component.html',
  styleUrls: ['./housing.component.scss'],
})
export class HousingComponent implements OnInit {
  HOUSES: House[] = [
    {
      address: '123 ave, Princeton, NJ, 00001',
      landlord: 'ABC',
      phone: '123-234-4567',
      numberOfPerson: 5,
      employee: [
        {
          name: 'Zack',
          phone: '123-234-1111',
          email: 'zack@beaconﬁre.com',
          car: 'Honda_Accord_Grey',
        },
      ],
      facility: [
        {
          numberOfBeds: 1,
          numberOfMattresses: 2,
          numberOfTables: 1,
          numberOfChairs: 1,
          reports: 'report',
        },
      ],
    },
    {
      address: '3824576sadfalsk hds',
      landlord: 'IJYHKJF',
      phone: '472-412-123',
      numberOfPerson: 2,
      employee: [
        {
          name: 'Zack',
          phone: '123-234-1111',
          email: 'zack@beaconﬁre.com',
          car: 'Honda_Accord_Grey',
        },
      ],
      facility: [
        {
          numberOfBeds: 1,
          numberOfMattresses: 2,
          numberOfTables: 1,
          numberOfChairs: 1,
          reports: 'report',
        },
      ],
    }
  ];

  cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return [
          { title: 'House Info', cols: 1, rows: 1 },
          { title: 'House Info', cols: 1, rows: 1 },
          { title: 'House Info', cols: 1, rows: 1 },
        ];
      }

      return [
        { title: 'House Info', cols: 1, rows: 1 },
        { title: 'House Info', cols: 1, rows: 1 },
        { title: 'House Info', cols: 1, rows: 1 },
      ];
    })
  );

  constructor(private breakpointObserver: BreakpointObserver) {}

  ngOnInit(): void {}
}
