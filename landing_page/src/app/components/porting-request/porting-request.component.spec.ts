import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PortingRequestComponent } from './porting-request.component';

describe('PortingRequestComponent', () => {
  let component: PortingRequestComponent;
  let fixture: ComponentFixture<PortingRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PortingRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PortingRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
