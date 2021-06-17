import { House } from './house';

export const HOUSES: House[] = [
  {
    id: 1,
    address: '123 ave, Princeton, NJ, 00001',
    landlord: 'ABC',
    phone: '123-234-4567',
    numberOfPerson: 5,
    employee: [
      {
        id: 12,
        name: 'Zack',
        phone: '123-234-1111',
        email: 'zack@beaconﬁre.com',
        car: 'Honda_Accord_Grey',
      },
    ],
    facility: [
      {
        id: 3,
        numberOfBeds: 1,
        numberOfMattresses: 2,
        numberOfTables: 1,
        numberOfChairs: 1,
        reports: 'report',
      },
    ],
  },
  {
    id: 9,
    address: '20 Lake View St.',
    landlord: 'Jason',
    phone: '472-412-123',
    numberOfPerson: 2,
    employee: [
      {
        id: 2,
        name: 'Zack',
        phone: '123-234-1111',
        email: 'zack@beaconﬁre.com',
        car: 'Honda_Accord_Grey',
      },
    ],
    facility: [
      {
        id: 4,
        numberOfBeds: 1,
        numberOfMattresses: 2,
        numberOfTables: 1,
        numberOfChairs: 1,
        reports: 'report',
      },
    ],
  },
  {
    id: 5,
    address: '34 Valley View Drive, Hamden, CT 06514',
    landlord: 'cfgasd',
    phone: '377-234-6746',
    numberOfPerson: 2,
    employee: [
      {
        id: 1,
        name: 'Zack',
        phone: '123-234-1111',
        email: 'zack@beaconﬁre.com',
        car: 'Honda_Accord_Grey',
      },
    ],
    facility: [
      {
        id: 5,
        numberOfBeds: 1,
        numberOfMattresses: 2,
        numberOfTables: 1,
        numberOfChairs: 1,
        reports: 'report',
      },
    ],
  },
];
