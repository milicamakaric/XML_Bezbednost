import { TestBed } from '@angular/core/testing';

import { AccommodationServiceService } from './accommodation-service.service';

describe('AccommodationServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AccommodationServiceService = TestBed.get(AccommodationServiceService);
    expect(service).toBeTruthy();
  });
});
