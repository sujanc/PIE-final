import { TestBed } from '@angular/core/testing';

import { InsurerOutgoingportingrequestService } from './insurer-outgoingportingrequest.service';

describe('InsurerOutgoingportingrequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InsurerOutgoingportingrequestService = TestBed.get(InsurerOutgoingportingrequestService);
    expect(service).toBeTruthy();
  });
});
