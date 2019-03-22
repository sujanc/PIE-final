import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomingPortingRequestsComponent } from './incoming-porting-requests.component';

describe('IncomingPortingRequestsComponent', () => {
  let component: IncomingPortingRequestsComponent;
  let fixture: ComponentFixture<IncomingPortingRequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncomingPortingRequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncomingPortingRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
