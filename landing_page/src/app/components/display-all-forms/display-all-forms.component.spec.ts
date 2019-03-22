import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayAllFormsComponent } from './display-all-forms.component';

describe('DisplayAllFormsComponent', () => {
  let component: DisplayAllFormsComponent;
  let fixture: ComponentFixture<DisplayAllFormsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayAllFormsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayAllFormsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
