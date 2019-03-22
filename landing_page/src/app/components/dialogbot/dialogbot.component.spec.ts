import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogbotComponent } from './dialogbot.component';

describe('DialogbotComponent', () => {
  let component: DialogbotComponent;
  let fixture: ComponentFixture<DialogbotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogbotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogbotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
