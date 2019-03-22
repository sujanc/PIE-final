import { TestBed } from '@angular/core/testing';

import { InsurerPolicyService } from './insurer-policy.service';

describe('InsurerPolicyService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InsurerPolicyService = TestBed.get(InsurerPolicyService);
    expect(service).toBeTruthy();
  });
});
