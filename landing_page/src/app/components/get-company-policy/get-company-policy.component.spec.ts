import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCompanyPolicyComponent } from './get-company-policy.component';

describe('GetCompanyPolicyComponent', () => {
  let component: GetCompanyPolicyComponent;
  let fixture: ComponentFixture<GetCompanyPolicyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetCompanyPolicyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetCompanyPolicyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
