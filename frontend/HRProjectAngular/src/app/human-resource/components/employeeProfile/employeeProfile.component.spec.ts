import { ComponentFixture, TestBed } from '@angular/core/testing';

import { employeeProfileComponent } from './employeeProfile.component';

describe('PersonalInfoComponent', () => {
  let component: employeeProfileComponent;
  let fixture: ComponentFixture<employeeProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ employeeProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(employeeProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
