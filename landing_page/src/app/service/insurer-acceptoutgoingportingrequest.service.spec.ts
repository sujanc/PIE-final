import { TestBed } from '@angular/core/testing';

import { InsurerAcceptoutgoingportingrequestService } from './insurer-acceptoutgoingportingrequest.service';

describe('InsurerAcceptoutgoingportingrequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InsurerAcceptoutgoingportingrequestService = TestBed.get(InsurerAcceptoutgoingportingrequestService);
    expect(service).toBeTruthy();
  });
});
