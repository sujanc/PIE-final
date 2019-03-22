import { TestBed } from '@angular/core/testing';

import { FetchPendingTasksService } from './fetch-pending-tasks.service';

describe('FetchPendingTasksService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FetchPendingTasksService = TestBed.get(FetchPendingTasksService);
    expect(service).toBeTruthy();
  });
});
