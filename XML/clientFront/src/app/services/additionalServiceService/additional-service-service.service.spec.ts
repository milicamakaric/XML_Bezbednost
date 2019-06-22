import { TestBed } from '@angular/core/testing';

import { AdditionalServiceServiceService } from './additional-service-service.service';

describe('AdditionalServiceServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdditionalServiceServiceService = TestBed.get(AdditionalServiceServiceService);
    expect(service).toBeTruthy();
  });
});
