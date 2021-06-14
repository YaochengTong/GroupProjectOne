import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseManagementComponent } from './houseManagement.component';

describe('HouseManagementComponent', () => {
  let component: HouseManagementComponent;
  let fixture: ComponentFixture<HouseManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HouseManagementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HouseManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
