import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayAllPortingRequestsComponent } from './display-all-porting-requests.component';

describe('DisplayAllPortingRequestsComponent', () => {
  let component: DisplayAllPortingRequestsComponent;
  let fixture: ComponentFixture<DisplayAllPortingRequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayAllPortingRequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayAllPortingRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
