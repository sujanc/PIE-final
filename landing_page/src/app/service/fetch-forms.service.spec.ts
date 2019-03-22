import { TestBed } from '@angular/core/testing';

import { FetchFormsService } from './fetch-forms.service';

describe('FetchFormsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FetchFormsService = TestBed.get(FetchFormsService);
    expect(service).toBeTruthy();
  });
});
