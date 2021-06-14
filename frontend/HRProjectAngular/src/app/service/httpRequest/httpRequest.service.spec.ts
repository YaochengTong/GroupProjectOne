import { TestBed } from '@angular/core/testing';

import { httpRequestService } from './httpRequest.service';

describe('httpRequestService', () => {
  let service: httpRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(httpRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
