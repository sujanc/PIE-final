import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PortingHistoryComponent } from './porting-history.component';

describe('PortingHistoryComponent', () => {
  let component: PortingHistoryComponent;
  let fixture: ComponentFixture<PortingHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PortingHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PortingHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
