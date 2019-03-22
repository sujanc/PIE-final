import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllPortingRequestsComponent } from './all-porting-requests.component';

describe('AllPortingRequestsComponent', () => {
  let component: AllPortingRequestsComponent;
  let fixture: ComponentFixture<AllPortingRequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllPortingRequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllPortingRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
