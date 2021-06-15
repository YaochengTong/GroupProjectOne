import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeNavComponent } from './nav.component';

describe('NavComponent', () => {
  let component: EmployeeNavComponent;
  let fixture: ComponentFixture<EmployeeNavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmployeeNavComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
