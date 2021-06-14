import { ComponentFixture, TestBed } from '@angular/core/testing';

import { visaStatusManagementComponent } from './visaStatusManagement.component';

describe('visaStatusManagementComponent', () => {
  let component: visaStatusManagementComponent;
  let fixture: ComponentFixture<visaStatusManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ visaStatusManagementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(visaStatusManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
