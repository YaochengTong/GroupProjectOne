import {House} from './house';

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
    address: '92 East Cemetery Road, Appleton, WI 54911',
    landlord: 'Jason',
    phone: '472-412-123',
    numberOfPerson: 2,
    employee: [
      {
        id: 2,
        name: 'asedr',
        phone: '123-234-1111',
        email: 'figapor810@beydent.com',
        car: 'Mada_asd',
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
        name: 'Gana',
        phone: '121-234-5555',
        email: 'mnajim.triand@azel.xyz',
        car: 'Benz_G63',
      },
      {
        id: 3,
        name: 'whs',
        phone: '121-234-5555',
        email: 'mnajim.triand@azel.xyz',
        car: 'Benz_G63',
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
