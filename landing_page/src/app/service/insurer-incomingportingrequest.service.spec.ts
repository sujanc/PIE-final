import { TestBed } from '@angular/core/testing';

import { InsurerIncomingportingrequestService } from './InsurerIncomingportingrequestService';

describe('InsurerIncomingportingrequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InsurerIncomingportingrequestService = TestBed.get(InsurerIncomingportingrequestService);
    expect(service).toBeTruthy();
  });
});
