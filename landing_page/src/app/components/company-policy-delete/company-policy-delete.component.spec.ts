import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyPolicyDeleteComponent } from './company-policy-delete.component';

describe('CompanyPolicyDeleteComponent', () => {
  let component: CompanyPolicyDeleteComponent;
  let fixture: ComponentFixture<CompanyPolicyDeleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompanyPolicyDeleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanyPolicyDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
