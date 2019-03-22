import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PolicybuymailComponent } from './policybuymail.component';

describe('PolicybuymailComponent', () => {
  let component: PolicybuymailComponent;
  let fixture: ComponentFixture<PolicybuymailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PolicybuymailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PolicybuymailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
