import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewFormFormatComponent } from './new-form-format.component';

describe('NewFormFormatComponent', () => {
  let component: NewFormFormatComponent;
  let fixture: ComponentFixture<NewFormFormatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewFormFormatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewFormFormatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
