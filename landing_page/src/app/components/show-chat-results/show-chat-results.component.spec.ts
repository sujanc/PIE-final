import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowChatResultsComponent } from './show-chat-results.component';

describe('ShowChatResultsComponent', () => {
  let component: ShowChatResultsComponent;
  let fixture: ComponentFixture<ShowChatResultsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowChatResultsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowChatResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
