import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OutgoingPortingRequestsComponent } from './outgoing-porting-requests.component';

describe('OutgoingPortingRequestsComponent', () => {
  let component: OutgoingPortingRequestsComponent;
  let fixture: ComponentFixture<OutgoingPortingRequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OutgoingPortingRequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OutgoingPortingRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
