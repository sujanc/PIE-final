import { TestBed } from '@angular/core/testing';

import { InsurerAcceptincomingportingrequestService } from './insurer-acceptincomingportingrequest.service';

describe('InsurerAcceptincomingportingrequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InsurerAcceptincomingportingrequestService = TestBed.get(InsurerAcceptincomingportingrequestService);
    expect(service).toBeTruthy();
  });
});
