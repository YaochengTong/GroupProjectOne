import { TestBed } from '@angular/core/testing';

import { HTTPReq } from './HTTPReq.service';

describe('HTTPReq', () => {
  let service: HTTPReq;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HTTPReq);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
