import { TestBed } from '@angular/core/testing';

import { InsurerRejectincomingportingrequestService} from "./InsurerRejectincomingportingrequestService";

describe('InsurerRejectincomingportingrequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InsurerRejectincomingportingrequestService = TestBed.get(InsurerRejectincomingportingrequestService);
    expect(service).toBeTruthy();
  });
});
